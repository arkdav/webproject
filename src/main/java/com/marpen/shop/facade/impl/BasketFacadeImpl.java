package com.marpen.shop.facade.impl;

import com.marpen.shop.dto.BasketDto;
import com.marpen.shop.dto.BasketProductDto;
import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.facade.BasketFacade;
import com.marpen.shop.facade.ProductFacade;
import com.marpen.shop.model.Cart;
import com.marpen.shop.model.CartEntry;
import com.marpen.shop.service.CartEntryService;
import com.marpen.shop.service.CartService;

import java.util.ArrayList;
import java.util.List;

public class BasketFacadeImpl implements BasketFacade {

    private CartService cartService;
    private CartEntryService cartEntryService;
    private ProductFacade productFacade;


    public BasketFacadeImpl(CartService cartService, CartEntryService cartEntryService, ProductFacade productFacade) {
        this.cartService = cartService;
        this.cartEntryService = cartEntryService;
        this.productFacade = productFacade;
    }

    @Override
    public BasketDto getBasketByUserId(int userId) {
        Cart cart = cartService.getCartByUserId(userId);
        List<CartEntry> cartEntries=null;
        if(cart!=null) {
            cartEntries = cartEntryService.getCartEntriesByCartId(cart.getCartId());
        } else {
            cartService.save(userId);
            cart=cartService.getCartByUserId(userId);
            cartEntries = cartEntryService.getCartEntriesByCartId(cart.getCartId());
        }
        BasketDto basketDto = fromCartAndEntriesToBasketDto(cart,cartEntries);
        return basketDto;
    }

    @Override
    public void addProductToBasket(int userId, int productId) {
        Cart cart = cartService.getCartByUserId(userId);
        CartEntry cartEntry=cartEntryService.getCartEntryByProductId(cart.getCartId(), productId);
        if(cartEntry!=null){
            cartEntry.setAmount(cartEntry.getAmount()+1);
            cartEntryService.updateCartEntry(cartEntry);
        } else {
            cartEntryService.save(cart.getCartId(), productId);
        }
    }

    private BasketDto fromCartAndEntriesToBasketDto(Cart cart, List<CartEntry> cartEntries) {
        BasketDto basketDto = new BasketDto();
        basketDto.setBasketId(cart.getCartId());
        basketDto.setUserId(cart.getUserId());
        basketDto.setDate(cart.getDate());
        List<BasketProductDto> products=new ArrayList<>();
        double basketPrice=0;
        if(cartEntries!=null) {
            for (CartEntry cartEntry :
                    cartEntries) {
                BasketProductDto basketProductDto = new BasketProductDto();
                basketProductDto.setAmount(cartEntry.getAmount());
                ProductDto productDto = productFacade.getProductById(cartEntry.getProductId());
                basketProductDto.setProductDto(productDto);
                basketProductDto.setTotalPrice(cartEntry.getAmount()*productDto.getPrice());
                basketPrice+=basketProductDto.getTotalPrice();
                products.add(basketProductDto);
            }
        }
        basketDto.setProducts(products);
        basketDto.setBasketPrice(basketPrice);
        return basketDto;
    }
}
