package com.marpen.shop.controller;

import com.marpen.shop.dto.CartDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import com.marpen.shop.facade.UserFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartOrderController {
    private UserFacade userFacade;
    private CartFacade cartFacade;
    private OrderFacade orderFacade;

    public CartOrderController(UserFacade userFacade,
                               CartFacade cartFacade,
                               OrderFacade orderFacade) {

        this.userFacade = userFacade;
        this.cartFacade = cartFacade;
        this.orderFacade = orderFacade;
    }

    @RequestMapping(value = "/addtobasket", method = RequestMethod.POST)
    public String addToCart(@RequestParam(value = "product_id") int productId) {
        cartFacade.addProductToCart(getUserId(), productId);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String removeFromCart(@RequestParam(value = "product_id") int productId) {
        cartFacade.removeProductFromCart(getUserId(), productId);
        return "redirect:/basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String giveCart(Model model) {
        CartDto cartDto = cartFacade.getCartByUserId(getUserId());
        model.addAttribute("basketList", cartDto);
        return "basket";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order() {
        orderFacade.addCartToOrder(getUserId());
        cartFacade.removeCart(getUserId());
        return "redirect:/userorders";
    }

    @RequestMapping(value = "/userorders", method = RequestMethod.GET)
    public String order(Model model) {
        //model.addAttribute("orderList");
        return "userorders";
    }

    private int getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userFacade.getUserInformation(auth.getName()).getUserId();
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
