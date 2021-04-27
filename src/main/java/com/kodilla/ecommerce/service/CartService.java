package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.*;
import com.kodilla.ecommerce.dto.CartDto;
import com.kodilla.ecommerce.dto.CartItemDto;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.mapper.CartItemMapper;
import com.kodilla.ecommerce.mapper.CartMapper;
import com.kodilla.ecommerce.repository.CartItemRepository;
import com.kodilla.ecommerce.repository.CartRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

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

    public Optional<CartDto> getCartByUser(final User user) {
        return cartRepository.findByUser(user)
                .map(cartMapper::mapToCartDto);
    }

    @SneakyThrows
    public Optional<CartDto> addItemToCart(final CartItemDto cartItemDto) {
        Optional<Cart> cart = cartRepository.findById(cartItemDto.getCartId());
        Optional<Product> product = productRepository.findById(cartItemDto.getProductId());
        if (cart.isPresent() && product.isPresent()) {
            cart.ifPresent(c -> {
                CartItem cartItem =cartItemMapper.mapToCartItem(cartItemDto,c,product.get());
                c.getItems().add(cartItem);
                cartItemRepository.save(cartItem);
            });
            return cart.map(cartMapper::mapToCartDto);
        }
        return Optional.empty();
    }

    @SneakyThrows
    public Optional<CartDto> deleteItemFromCart(final CartItemDto cartItemDto) {
        Optional<Cart> cart = cartRepository.findById(cartItemDto.getCartId());
        Optional<Product> product = productRepository.findById(cartItemDto.getProductId());
        if (cart.isPresent() && product.isPresent()) {
            cart.ifPresent(c -> {
                c.getItems().remove(cartItemMapper.mapToCartItem(cartItemDto,c,product.get()));
                cartItemRepository.deleteById(cartItemDto.getId());
            });
            return cart.map(cartMapper::mapToCartDto);
        }
        return Optional.empty();
    }

    public void deleteCartById(final Long id) {
        cartRepository.deleteById(id);
    }

}
