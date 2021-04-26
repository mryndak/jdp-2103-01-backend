package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/carts", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@Api(value = "This is Cart Controller - Here we can do operations on cart")
public class CartController {

    @GetMapping
    @ApiOperation(value = "This lists our current carts", response = List.class)
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
    @ApiOperation(value = "This allows us to get cart by id", response = CartDto.class)
    public CartDto getCart(@PathVariable Long id) {
        return CartDto.builder()
                .id(1L)
                .build();
    }

    @PostMapping
    @ApiOperation(value = "This will create a brand new cart", response = CartDto.class)
    public CartDto createCart(@RequestBody final CartDto cartDto) {
        return CartDto.builder()
                .id(1L)
                .userId(1L)
                .build();
    }

    @PutMapping("/add")
    @ApiOperation(value = "This allows us to add an item to the cart", response = CartDto.class)
    public CartDto addItemToCart(@RequestBody final CartItemDto cartItemDto) {
        return CartDto.builder()
                .id(1L)
                .userId(1L)
                .build();
    }

    @PutMapping("/remove")
    @ApiOperation(value = "This removes the item from the cart", response = CartDto.class)
    public CartDto deleteItemFromCart(@RequestBody final CartItemDto cartItemDto) {
        return CartDto.builder()
                .id(1L)
                .build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "This allows us to delete our cart by providing id")
    public void deleteCart(@PathVariable final Long id) {
    }
}