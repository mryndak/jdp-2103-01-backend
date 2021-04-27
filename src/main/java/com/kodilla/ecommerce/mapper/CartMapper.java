package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.*;
import com.kodilla.ecommerce.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "userId", source = "user.id")
    CartDto mapToCartDto(final Cart cart);

    @Mapping(target = "items", source = "items")
    @Mapping(target = "userId", source = "cart.user.id")
    CartDto mapToCartDto(final Cart cart, final List<CartItemDto> items);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "items", ignore = true)
    Cart mapToCart(final CartDto cartDto);

    @Mapping(target = "id", source = "cartDto.id")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "items", source = "items")
    Cart mapToCart(final CartDto cartDto, final List<CartItem> items, final User user);

}