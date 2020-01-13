package com.marpen.shop.controller;

import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.OrderNoteDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartOrderController {
    private CartFacade cartFacade;
    private OrderFacade orderFacade;

    public CartOrderController(CartFacade cartFacade,
                               OrderFacade orderFacade) {
        this.cartFacade = cartFacade;
        this.orderFacade = orderFacade;
    }

    @RequestMapping(value = "/addtocart", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> addToCart(@RequestParam(value = "product_id") int productId,
                                  @RequestParam(value = "amount", required = false, defaultValue = "1") int amount) {
        cartFacade.getCartByUserLogin(getUserLogin());
        cartFacade.addProductToCart(getUserLogin(), productId, amount);
        List<String> priceAndAmount = new ArrayList<>();
        priceAndAmount.add(cartFacade.getCartByUserLogin(getUserLogin()).getCartPrice().toString());
        priceAndAmount.add(Integer.toString(cartFacade.getCartProductsAmount(getUserLogin())));
        return (cartFacade.productIsInUserCart(getUserLogin(), productId)) ? priceAndAmount : null;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String removeFromCart(@RequestParam(value = "product_id") int productId) {
        cartFacade.removeProductFromCart(getUserLogin(), productId);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String giveCart(Model model) {
        CartDto cartDto = cartFacade.getCartByUserLogin(getUserLogin());
        model.addAttribute("cartList", cartDto);
        return "cart";
    }

    @RequestMapping(value = "/cartamount", method = RequestMethod.GET)
    public String newAmountInCart(@RequestParam(value = "product_id") int productId,
                                  @RequestParam(value = "productAmount", required = false, defaultValue = "1")
                                          String productAmountString) {
        int productAmount = Integer.parseInt(productAmountString.trim());
        if (productAmount == 0) {
            cartFacade.removeProductFromCart(getUserLogin(), productId);
        } else if (productAmount > 0) {
            cartFacade.updateProductInCart(getUserLogin(), productId, productAmount);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getOrder(Model model) {
        model.addAttribute("orderList", cartFacade.getCartByUserLogin(getUserLogin()));
        model.addAttribute("order", new OrderNoteDto());
        return "orderconfirmation";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(@ModelAttribute("order") OrderNoteDto orderNoteDto) {
        orderFacade.addCartToOrder(getUserLogin(), orderNoteDto.getText());
        return "redirect:/userorders";
    }

    @RequestMapping(value = "/userorders", method = RequestMethod.GET)
    public String order(@RequestParam(name = "dateFrom", required = false, defaultValue = "") String dateFrom,
                        @RequestParam(name = "dateTo", required = false, defaultValue = "") String dateTo,
                        @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageId,
                        @RequestParam(value = "perpage", required = false, defaultValue = "5") Integer ordersPerPage,
                        Model model) {
        System.out.println(dateFrom);
        System.out.println(dateTo);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("page", pageId);
        pageId = (pageId != 1) ? (pageId - 1) * ordersPerPage + 1 : pageId;
        model.addAttribute("orders", orderFacade.getOrdersByUserLoginAndDate(dateFrom, dateTo, getUserLogin(), pageId, ordersPerPage));
        model.addAttribute("pagesList", orderFacade.getOrdersPagesList(dateFrom, dateTo, getUserLogin(), ordersPerPage));
        return "userorders";
    }

    @RequestMapping(value = "/businessorders", method = RequestMethod.GET)
    public String getBusinessOrders(@RequestParam(name = "sort", required = false, defaultValue = "") String status,
                                    @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageId,
                                    @RequestParam(value = "perpage", required = false, defaultValue = "10") Integer ordersPerPage,
                                    Model model) {
        model.addAttribute("page", pageId);
        pageId = (pageId != 1) ? (pageId - 1) * ordersPerPage + 1 : pageId;
        model.addAttribute("sort", status);
        model.addAttribute("businessOrdersList", orderFacade.getBusinessOrders(status, getUserLogin(), pageId, ordersPerPage));
        model.addAttribute("pagesList", orderFacade.getBusinessPagesList(status, getUserLogin(), ordersPerPage));
        return "businessorders";
    }

    @RequestMapping(value = "/businessorders/update", method = RequestMethod.GET)
    public String updateOrderEntryStatus(@RequestParam("orderId") Integer orderId) {
        orderFacade.changeOrderStatus(orderId);
        return "redirect:/businessorders";
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
