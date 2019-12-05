package com.marpen.shop.controller;

import com.marpen.shop.dto.BusinessOrderDto;
import com.marpen.shop.dto.BusinessProductDto;
import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.OrderNoteDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.OrderFacade;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.model.Product;
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

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getOrder(Model model) {
        model.addAttribute("orderList",cartFacade.getCartByUserLogin(getUserLogin()));
        model.addAttribute("order", new OrderNoteDto());
        return "orderconfirmation";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(@ModelAttribute("order") OrderNoteDto orderNoteDto) {
        orderFacade.addCartToOrder(getUserLogin(), orderNoteDto.getText());
        cartFacade.removeCart(getUserLogin());
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
    public String updateOrderEntryStatus(@RequestParam("entryId") Integer orderEntryId) {

        return "redirect:/businessorders";
    }

    private String getUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
