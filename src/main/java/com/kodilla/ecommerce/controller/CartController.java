package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/carts", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value = "This is Cart Controller - Here we can do operations on cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    @ApiOperation(value = "This lists our current carts", response = List.class)
    public List<CartDto> getCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "This allows us to get cart by id", response = CartDto.class)
    public CartDto getCart(@PathVariable Long id) {
        return cartService.getCartById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart with id: " + id + " doesn't exist."));
    }

    @PutMapping("/add")
    @ApiOperation(value = "This allows us to add an item to the cart", response = CartDto.class)
    public CartDto addItemToCart(@RequestBody final CartItemDto cartItemDto) {
        return cartService.addItemToCart(cartItemDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/remove")
    @ApiOperation(value = "This removes the item from the cart", response = CartDto.class)
    public CartDto deleteItemFromCart(@RequestBody final CartItemDto cartItemDto) {
        return cartService.deleteItemFromCart(cartItemDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "This allows us to delete our cart by providing id")
    public void deleteCart(@PathVariable final Long id) {
        cartService.deleteCartById(id);
    }
}