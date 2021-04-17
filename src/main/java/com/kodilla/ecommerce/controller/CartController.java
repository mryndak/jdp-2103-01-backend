package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/carts", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartDto> getCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable Long id) {
        return cartService.getCartById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart with id: " + "dosent exist."));
    }

    @PostMapping
    public CartDto createCart(@RequestBody final CartDto cartDto) {
        return cartService.saveCart(cartDto);
    }





//    @PutMapping("/add")
//    public CartDto addItemToCart(@RequestBody final CartItemDto cartItemDto) {
//        return CartDto.builder()
//                .id(1L)
//                .user(new User())
//                .build();
//    }




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