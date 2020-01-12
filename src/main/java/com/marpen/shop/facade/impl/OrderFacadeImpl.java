package com.marpen.shop.facade.impl;

import com.marpen.shop.converter.FromCartDto;
import com.marpen.shop.converter.FromCartProductDto;
import com.marpen.shop.converter.ToBusinessOrderDto;
import com.marpen.shop.converter.ToBusinessOrderProductDto;
import com.marpen.shop.converter.ToOrderDto;
import com.marpen.shop.converter.ToPageDto;
import com.marpen.shop.dto.BusinessOrderDto;
import com.marpen.shop.dto.BusinessOrderProductDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.CartProductDto;
import com.marpen.shop.dto.OrderDto;
import com.marpen.shop.dto.PageDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.model.Order;
import com.marpen.shop.model.OrderBundle;
import com.marpen.shop.model.OrderEntry;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.OrderBundleService;
import com.marpen.shop.service.OrderEntryService;
import com.marpen.shop.service.OrderService;
import com.marpen.shop.service.ProductService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class OrderFacadeImpl implements OrderFacade {

    private OrderBundleService orderBundleService;
    private OrderService orderService;
    private OrderEntryService orderEntryService;
    private ProductService productService;
    private CartFacade cartFacade;
    private ToOrderDto toOrderDto;
    private ToPageDto toPageDto;
    private FromCartDto fromCartDto;
    private ToBusinessOrderDto toBusinessOrderDto;


    public OrderFacadeImpl(OrderBundleService orderBundleService, OrderService orderService,
                           OrderEntryService orderEntryService, ProductService productService,
                           CartFacade cartFacade, ToOrderDto toOrderDto, ToPageDto toPageDto,
                           FromCartDto fromCartDto, ToBusinessOrderDto toBusinessOrderDto) {
        this.orderBundleService = orderBundleService;
        this.orderService = orderService;
        this.orderEntryService = orderEntryService;
        this.productService = productService;
        this.cartFacade = cartFacade;
        this.toOrderDto = toOrderDto;
        this.toPageDto = toPageDto;
        this.fromCartDto = fromCartDto;
        this.toBusinessOrderDto = toBusinessOrderDto;
    }

    @Override
    public void addCartToOrder(String userLogin, String orderNote) {
        CartDto cartDto = cartFacade.getCartByUserLogin(userLogin);
        OrderBundle orderBundle = fromCartDto.convert(cartDto);
        orderBundle.setOrderNote(orderNote);
        int orderBundleId = orderBundleService.save(orderBundle);

        List<CartProductDto> cartProductDtos = cartDto.getProducts();
        if (!cartProductDtos.isEmpty()) {
            for (CartProductDto cartProductDto :
                    cartProductDtos) {
                Product product = productService.getProductById(cartProductDto.getProductDto().getProductId());
                List<Order> orders = orderService.getOrdersByOrderBundleId(orderBundleId);
                boolean isOrderEntry = false;
                if (!orders.isEmpty()) {
                    for (Order order :
                            orders) {
                        if (product.getUserLogin().equals(order.getOwnerLogin())) {
                            orderEntryService.save(order.getOrderId(), product.getProductId(), cartProductDto.getAmount());
                            isOrderEntry = true;
                            break;
                        }
                    }
                    if (!isOrderEntry) {
                        int orderId = orderService.save(orderBundleId, product.getUserLogin(), cartProductDto.getTotalPrice());
                        orderEntryService.save(orderId, product.getProductId(), cartProductDto.getAmount());
                    }
                } else {
                    int orderId = orderService.save(orderBundleId, product.getUserLogin(), cartProductDto.getTotalPrice());
                    orderEntryService.save(orderId, product.getProductId(), cartProductDto.getAmount());
                }
            }
        }
        cartFacade.removeCart(userLogin);
    }

    @Override
    public List<OrderDto> getOrdersByUserLoginAndDate(String dateFromString, String dateToString, String userLogin, int page, int perPage) {
        Date dateFrom, dateTo;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dateFrom = dateFromString.isEmpty() ?  new GregorianCalendar(2019, Calendar.OCTOBER, 1).getTime() : format.parse(dateFromString);
            dateTo = dateToString.isEmpty() ? new Date() : format.parse(dateToString);
        } catch (ParseException exc) {
            dateTo = dateFrom = new Date();
        }
        List<OrderBundle> orderBundles = orderBundleService.getOrderBundlesByUserLoginAndDate(dateFrom, dateTo, userLogin, page, perPage);
        List<OrderDto> orderBundleDtos = new ArrayList<>();
        if (!orderBundles.isEmpty()) {
            for (OrderBundle orderBundle :
                    orderBundles) {
                List<OrderDto> orderDtos = toOrderDto.convert(orderBundle);
                orderBundleDtos.addAll(orderDtos);
            }
        }
        return orderBundleDtos;
    }

    @Override
    public List<BusinessOrderDto> getBusinessOrders(String status, String login, int pageId, int ordersPerPage) {
        List<BusinessOrderDto> businessOrderDtos = new ArrayList<>();
        if (!status.isEmpty()) {
            status = status.equals("col") ? "collected" : status.equals("proc") ? "processing" : "";
        }
        List<Order> allBusinessOrders = orderService.getOrders(status, login, pageId, ordersPerPage);
        for (Order order :
                allBusinessOrders) {
            BusinessOrderDto businessOrderDto = toBusinessOrderDto.convert(order);
            businessOrderDtos.add(businessOrderDto);
        }
        return businessOrderDtos;
    }

    @Override
    public void changeOrderStatus(int orderId) {
        Order order = orderService.getOrder(orderId);
        orderService.changeOrderStatus(order);
    }

    @Override
    public List<PageDto> getOrdersPagesList(String dateFromString, String dateToString, String userLogin, int ordersPerPage) {
        Date dateFrom, dateTo;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dateFrom = dateFromString.isEmpty() ?  new GregorianCalendar(2019, Calendar.OCTOBER, 1).getTime() : format.parse(dateFromString);
            dateTo = dateToString.isEmpty() ? new Date() : format.parse(dateToString);
        } catch (ParseException exc) {
            dateTo = dateFrom = new Date();
        }
        int amountOfProducts = orderBundleService.getAmountOfOrderBundlesByLoginAndDate(dateFrom, dateTo, userLogin);
        return toPageDto.convert(amountOfProducts, ordersPerPage);
    }

    @Override
    public List<PageDto> getBusinessPagesList(String status, String userLogin, int productsPerPage) {
        if (!status.isEmpty()) {
            status = status.equals("col") ? "collected" : status.equals("proc") ? "processing" : "";
        }
        int amountOfProducts = this.orderService.getBusinessOrdersAmount(userLogin, status);
        return toPageDto.convert(amountOfProducts, productsPerPage);
    }
}
