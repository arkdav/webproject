package com.marpen.shop.facade.impl;

import com.marpen.shop.converter.ToCartDto;
import com.marpen.shop.dto.CartDto;
import com.marpen.shop.facade.CartFacade;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.model.Cart;
import com.marpen.shop.model.CartEntry;
import com.marpen.shop.service.CartEntryService;
import com.marpen.shop.service.CartService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CartFacadeImpl implements CartFacade {

    private CartService cartService;
    private CartEntryService cartEntryService;
    private ProductFacade productFacade;
    private ToCartDto toCartDto;


    public CartFacadeImpl(CartService cartService, CartEntryService cartEntryService, ProductFacade productFacade,
                          ToCartDto toCartDto) {
        this.cartService = cartService;
        this.cartEntryService = cartEntryService;
        this.productFacade = productFacade;
        this.toCartDto = toCartDto;
    }

    @Override
    public CartDto getCartByUserId(int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null) {
            cartService.save(userId);
            cart = cartService.getCartByUserId(userId);
        }
        return toCartDto.convert(cart);
    }

    @Override
    public void addProductToCart(int userId, int productId) {
        Cart cart = cartService.getCartByUserId(userId);
        CartEntry cartEntryProduct = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        double newTotalPrice;
        if (cartEntryProduct != null) {
            cartEntryProduct.setAmount(cartEntryProduct.getAmount() + 1);
            cartEntryService.updateCartEntry(cartEntryProduct);
            newTotalPrice = cart.getTotalPrice() + Double.parseDouble(productFacade.getProductById(productId).getPrice());
        } else {
            cartEntryService.save(cart.getCartId(), productId);
            cartEntryProduct = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
            newTotalPrice = cart.getTotalPrice() + cartEntryProduct.getAmount() * Double.parseDouble(productFacade.getProductById(productId).getPrice());
        }
        cart.setTotalPrice(newTotalPrice);
        cartService.updateCart(cart);
    }

    @Override
    public void updateProductInCart(int userId, int productId, int productAmount) {
        Cart cart = cartService.getCartByUserId(userId);
        CartEntry cartEntryProduct = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        double totalPrice = cart.getTotalPrice() - cartEntryProduct.getAmount() * Double.parseDouble(productFacade.getProductById(productId).getPrice());
        cartEntryProduct.setAmount(productAmount);
        cartEntryService.updateCartEntry(cartEntryProduct);
        totalPrice += cartEntryProduct.getAmount() * Double.parseDouble(productFacade.getProductById(productId).getPrice());
        cart.setTotalPrice(new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP).doubleValue());
        cartService.updateCart(cart);
    }

    @Override
    public void removeProductFromCart(int userId, int productId) {
        Cart cart = cartService.getCartByUserId(userId);
        CartEntry cartEntry = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        cartEntryService.removeCartEntry(cartEntry);
        cart.setTotalPrice(cart.getTotalPrice() - cartEntry.getAmount() * Double.parseDouble(productFacade.getProductById(productId).getPrice()));
        cartService.updateCart(cart);
    }

    @Override
    public void removeCart(int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        cartEntryService.removeCartEntries(cart.getCartId());
        cartService.removeCart(userId);
    }
}
