package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.dto.OrderItemDto;
import com.kodilla.ecommerce.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "userId", source = "user.id")
    OrderDto mapToOrderDto(final Order order);

    @Mapping(target = "items", source = "items")
    @Mapping(target = "userId", source = "order.user.id")
    OrderDto mapToOrderDto(final Order order, final List<OrderItemDto> items);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order mapToOrder(final OrderDto orderDto);

    @Mapping(target = "id", source = "orderDto.id")
    @Mapping(target = "status", source = "orderDto.status")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "items", source = "items")
    Order mapToOrder(final OrderDto orderDto, final List<OrderItem> items, final User user);

}