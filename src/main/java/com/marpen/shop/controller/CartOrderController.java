package com.marpen.shop.controller;

import com.marpen.shop.dto.CartDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartOrderController {
    private CartFacade cartFacade;
    private OrderFacade orderFacade;

    public CartOrderController(CartFacade cartFacade,
                               OrderFacade orderFacade) {
        this.cartFacade = cartFacade;
        this.orderFacade = orderFacade;
    }

    @RequestMapping(value = "/addtobasket", method = RequestMethod.POST)
    public String addToCart(@RequestParam(value = "product_id") int productId) {
        CartDto cartDto = cartFacade.getCartByUserLogin(getUserLogin());
        cartFacade.addProductToCart(getUserLogin(), productId);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String removeFromCart(@RequestParam(value = "product_id") int productId) {
        cartFacade.removeProductFromCart(getUserLogin(), productId);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String giveCart(Model model) {
        CartDto cartDto = cartFacade.getCartByUserLogin(getUserLogin());
        model.addAttribute("basketList", cartDto);
        return "basket";
    }

    @RequestMapping(value = "/basketamount", method = RequestMethod.GET)
    public String newAmountInCart(@RequestParam(value = "product_id") int productId,
                                  @RequestParam(value = "productAmount", required = false, defaultValue = "1")
                                          String productAmountString) {
        Integer productAmount = Integer.valueOf(productAmountString.trim());
        if (productAmount == 0) {
            cartFacade.removeProductFromCart(getUserLogin(), productId);
        } else if (productAmount > 0) {
            cartFacade.updateProductInCart(getUserLogin(), productId, productAmount);
        }
        return "redirect:/basket";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order() {
        orderFacade.addCartToOrder(getUserLogin());
        cartFacade.removeCart(getUserLogin());
        return "redirect:/userorders";
    }

    @RequestMapping(value = "/userorders", method = RequestMethod.GET)
    public String order(Model model) {
        model.addAttribute("orders", orderFacade.getOrdersByUserLogin(getUserLogin()));
        return "userorders";
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
