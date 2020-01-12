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
import java.util.List;

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
    public CartDto getCartByUserLogin(String userLogin) {
        Cart cart = cartService.getCartByUserLogin(userLogin);
        if (cart == null) {
            cartService.save(userLogin);
            cart = cartService.getCartByUserLogin(userLogin);
        }
        return toCartDto.convert(cart);
    }

    @Override
    public void addProductToCart(String userLogin, int productId, int productAmount) {
        Cart cart = cartService.getCartByUserLogin(userLogin);
        CartEntry cartEntryProduct = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        double newTotalPrice;
        if (cartEntryProduct != null) {
            cartEntryProduct.setAmount(cartEntryProduct.getAmount() + productAmount);
            cartEntryService.updateCartEntry(cartEntryProduct);
            newTotalPrice = cart.getTotalPrice() + Double.parseDouble(productFacade.getProductById(productId).getPrice());
        } else {
            cartEntryService.save(cart.getCartId(), productId, productAmount);
            cartEntryProduct = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
            newTotalPrice = cart.getTotalPrice() + cartEntryProduct.getAmount() * Double.parseDouble(productFacade.getProductById(productId).getPrice());
        }
        cart.setTotalPrice(newTotalPrice);
        cartService.updateCart(cart);
    }

    @Override
    public void updateProductInCart(String userLogin, int productId, int productAmount) {
        Cart cart = cartService.getCartByUserLogin(userLogin);
        CartEntry cartEntryProduct = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        double totalPrice = cart.getTotalPrice() - cartEntryProduct.getAmount() * Double.parseDouble(productFacade.getProductById(productId).getPrice());
        cartEntryProduct.setAmount(productAmount);
        cartEntryService.updateCartEntry(cartEntryProduct);
        totalPrice += cartEntryProduct.getAmount() * Double.parseDouble(productFacade.getProductById(productId).getPrice());
        cart.setTotalPrice(new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP).doubleValue());
        cartService.updateCart(cart);
    }

    @Override
    public void removeProductFromCart(String userLogin, int productId) {
        Cart cart = cartService.getCartByUserLogin(userLogin);
        CartEntry cartEntry = cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        cartEntryService.removeCartEntry(cartEntry);
        cart.setTotalPrice(cart.getTotalPrice() - cartEntry.getAmount() * Double.parseDouble(productFacade.getProductById(productId).getPrice()));
        cartService.updateCart(cart);
    }

    @Override
    public void removeCart(String userLogin) {
        Cart cart = cartService.getCartByUserLogin(userLogin);
        cartEntryService.removeCartEntries(cart.getCartId());
        cartService.removeCart(userLogin);
    }

    @Override
    public int getCartProductsAmount(String userLogin){
        Cart cart = cartService.getCartByUserLogin(userLogin);
        List<CartEntry> cartEntries = cartEntryService.getCartEntriesByCartId(cart.getCartId());
        return cartEntries.size();
    }

    @Override
    public boolean productIsInUserCart(String userLogin, int productId){
        Cart cart = cartService.getCartByUserLogin(userLogin);
        List<CartEntry> cartEntries = cartEntryService.getCartEntriesByCartId(cart.getCartId());
        boolean isInCart=false;
        for (CartEntry cartEntry:
             cartEntries) {
            if(cartEntry.getProductId()==productId){
                isInCart=true;
                break;
            }
        }
        return isInCart;
    }
}
