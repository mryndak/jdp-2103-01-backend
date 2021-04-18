package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.*;
import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.mapper.CartMapper;
import com.kodilla.ecommerce.repository.CartItemRepository;
import com.kodilla.ecommerce.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private CartMapper cartMapper;

    public List<CartDto> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::mapToCartDto)
                .collect(Collectors.toList());
    }

    public Optional<CartDto> getCartById(final Long id) {
        return cartRepository.findById(id)
                .map(cartMapper::mapToCartDto);
    }

    public CartDto saveCart(final CartDto cart) {
        Cart cartToSave = cartMapper.mapToCart(cart);
        cartToSave = cartRepository.save(cartToSave);
        return cartMapper.mapToCartDto(cartToSave);
    }

    public void addItemToCart(Long cartId, Long productId) throws CartNotFoundException, ItemNotFoundException {
        Cart cartWithItemToAdd = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        CartItem itemToAdd = cartItemRepository.findById(productId).orElseThrow(ItemNotFoundException::new);
        cartWithItemToAdd.getItems().add(itemToAdd);
        cartRepository.save(cartWithItemToAdd);
    }

    public void removeProductFromCart(Long cartId, Long productId) throws CartNotFoundException, ItemNotFoundException {
        Cart cartWithItemToRemove = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        CartItem itemToRemove = cartItemRepository.findById(productId).orElseThrow(ItemNotFoundException::new);
        cartWithItemToRemove.getItems().remove(itemToRemove);
        cartRepository.save(cartWithItemToRemove);
    }
}
