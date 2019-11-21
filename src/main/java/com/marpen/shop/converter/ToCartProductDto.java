package com.marpen.shop.converter;

import com.marpen.shop.dto.CartProductDto;
import com.marpen.shop.dto.ProductDto;
import com.marpen.shop.model.CartEntry;
import com.marpen.shop.model.Product;
import com.marpen.shop.service.ProductService;
import org.springframework.core.convert.converter.Converter;

public class ToCartProductDto implements Converter<CartEntry, CartProductDto> {

    private ProductService productService;
    private ToProductDto toProductDto;

    public ToCartProductDto(ProductService productService, ToProductDto toProductDto) {
        this.productService = productService;
        this.toProductDto = toProductDto;
    }

    public CartProductDto convert(CartEntry cartEntry) {
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setAmount(cartEntry.getAmount());
        Product product = productService.getProductById(cartEntry.getProductId());
        ProductDto productDto = toProductDto.convert(product);
        cartProductDto.setProductDto(productDto);
        cartProductDto.setTotalPrice(cartEntry.getAmount() * Double.parseDouble(productDto.getPrice()));
        return cartProductDto;
    }

}
