package com.marpen.shop.facade.impl;

import com.marpen.shop.dto.CartDto;
import com.marpen.shop.dto.CartProductDto;
import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.model.Cart;
import com.marpen.shop.model.CartEntry;
import com.marpen.shop.service.CartEntryService;
import com.marpen.shop.service.CartService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartFacadeImpl implements CartFacade {

    private CartService cartService;
    private CartEntryService cartEntryService;
    private ProductFacade productFacade;


    public CartFacadeImpl(CartService cartService, CartEntryService cartEntryService, ProductFacade productFacade) {
        this.cartService = cartService;
        this.cartEntryService = cartEntryService;
        this.productFacade = productFacade;
    }

    @Override
    public CartDto getCartByUserId(int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        List<CartEntry> cartEntries;
        if (cart != null) {
            cartEntries = cartEntryService.getCartEntriesByCartId(cart.getCartId());
        } else {
            cartService.save(userId);
            cart = cartService.getCartByUserId(userId);
            cartEntries = cartEntryService.getCartEntriesByCartId(cart.getCartId());
        }
        CartDto cartDto = fromCartAndEntriesToCartDto(cart, cartEntries);
        return cartDto;
    }

    @Override
    public void addProductToCart(int userId, int productId) {
        Cart cart = cartService.getCartByUserId(userId);
        CartEntry cartEntryProduct = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        Double newTotalPrice;
        if (cartEntryProduct != null) {
            cartEntryProduct.setAmount(cartEntryProduct.getAmount() + 1);
            cartEntryService.updateCartEntry(cartEntryProduct);
            newTotalPrice=cart.getTotalPrice()+productFacade.getProductById(productId).getPrice();
        } else {
            cartEntryService.save(cart.getCartId(), productId);
            cartEntryProduct=cartEntryService.getCartEntryByProductId(cart.getCartId(),productId);
            newTotalPrice=cart.getTotalPrice()+cartEntryProduct.getAmount()*productFacade.getProductById(productId).getPrice();
        }
        cart.setTotalPrice(newTotalPrice);
        cartService.updateCart(cart);
    }

    @Override
    public void removeProductFromCart(int userId, int productId) {
        Cart cart = cartService.getCartByUserId(userId);
        CartEntry cartEntry = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        cartEntryService.removeCartEntry(cartEntry);
        cart.setTotalPrice(cart.getTotalPrice()-cartEntry.getAmount()*productFacade.getProductById(cartEntry.getProductId()).getPrice());
        cartService.updateCart(cart);
    }

    @Override
    public void removeCart(int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        cartEntryService.removeCartEntries(cart.getCartId());
        cartService.removeCart(userId);
    }

    private CartDto fromCartAndEntriesToCartDto(Cart cart, List<CartEntry> cartEntries) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setUserId(cart.getUserId());
        Date date=cart.getDate();
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yyyy");
        cartDto.setDate(formatForDate.format(date));
        String formattedDouble=String.format("%.2f", cart.getTotalPrice());
        cartDto.setCartPrice(formattedDouble);
        List<CartProductDto> products = new ArrayList<>();
        if (!cartEntries.isEmpty()) {
            for (CartEntry cartEntry :
                    cartEntries) {
                CartProductDto cartProductDto = new CartProductDto();
                cartProductDto.setAmount(cartEntry.getAmount());
                ProductDto productDto = productFacade.getProductById(cartEntry.getProductId());
                cartProductDto.setProductDto(productDto);
                cartProductDto.setTotalPrice(cartEntry.getAmount() * productDto.getPrice());
                products.add(cartProductDto);
            }
        }
        cartDto.setProducts(products);
        return cartDto;
    }
}
