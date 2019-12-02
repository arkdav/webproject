package com.marpen.shop.converter;

import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.CartProductDto;
import com.marpen.shop.model.Cart;
import com.marpen.shop.model.CartEntry;
import com.marpen.shop.service.CartEntryService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ToCartDto implements Converter<Cart, CartDto> {

    private CartEntryService cartEntryService;
    private ToCartProductDto toCartProductDto;

    public ToCartDto(CartEntryService cartEntryService, ToCartProductDto toCartProductDto) {
        this.cartEntryService = cartEntryService;
        this.toCartProductDto = toCartProductDto;
    }

    @Override
    public CartDto convert(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setUserLogin(cart.getUserLogin());
        cartDto.setDate(cart.getDate());
        cartDto.setCartPrice(cart.getTotalPrice());
        List<CartEntry> cartEntries = cartEntryService.getCartEntriesByCartId(cart.getCartId());
        List<CartProductDto> products = new ArrayList<>();
        if (!cartEntries.isEmpty()) {
            for (CartEntry cartEntry :
                    cartEntries) {
                CartProductDto cartProductDto = toCartProductDto.convert(cartEntry);
                products.add(cartProductDto);
            }
        }
        cartDto.setProducts(products);
        return cartDto;
    }

}
