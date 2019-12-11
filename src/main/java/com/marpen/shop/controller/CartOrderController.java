package com.marpen.shop.controller;

import com.marpen.shop.dto.BusinessOrderDto;
import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.OrderNoteDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/addtocart", method = RequestMethod.POST)
    public String addToCart(@RequestParam(value = "product_id") int productId) {
        CartDto cartDto = cartFacade.getCartByUserLogin(getUserLogin());
        cartFacade.addProductToCart(getUserLogin(), productId);
        return "redirect:/cart";
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
        Integer productAmount = Integer.valueOf(productAmountString.trim());
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
    public String order(Model model) {
        model.addAttribute("orders", orderFacade.getOrdersByUserLogin(getUserLogin()));
        return "userorders";
    }

    @RequestMapping(value = "/businessorders", method = RequestMethod.GET)
    public String getBusinessOrders(Model model) {
        List<BusinessOrderDto> orderEntries = orderFacade.getBusinessOrders(getUserLogin());
        model.addAttribute("businessOrdersList", orderEntries);
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
