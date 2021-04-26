package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/carts", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class CartController {

    @GetMapping
    public List<CartDto> getCarts() {
        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("kurtka zimowa")
                .description("")
                .price(new BigDecimal(10))
                .build();

        List<CartItemDto> cartItemDto = Arrays.asList(CartItemDto.builder()
                .id(1L)
                .cartId(1L)
                .productId(1L)
                .quantity(10)
                .totalPrice(new BigDecimal(10))
                .build()
        );

        return Arrays.asList(
                CartDto.builder()
                        .id(1L)
                        .userId(1L)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable Long id) {
        return CartDto.builder()
                .id(1L)
                .build();
    }

    @PostMapping
    public CartDto createCart(@RequestBody final CartDto cartDto) {
        return CartDto.builder()
                .id(1L)
                .userId(1L)
                .build();
    }

    @PutMapping("/add")
    public CartDto addItemToCart(@RequestBody final CartItemDto cartItemDto) {
        return CartDto.builder()
                .id(1L)
                .userId(1L)
                .build();
    }

    @PutMapping("/remove")
    public CartDto deleteItemFromCart(@RequestBody final CartItemDto cartItemDto) {
        return CartDto.builder()
                .id(1L)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable final Long id) {
    }
}