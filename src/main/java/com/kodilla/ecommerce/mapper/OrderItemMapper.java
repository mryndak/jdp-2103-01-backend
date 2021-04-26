package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.dto.OrderItemDto;
import com.kodilla.ecommerce.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    //@Mapping(target = "price", ignore = true)
    //@Mapping(target = "quantity", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem mapToOrderItem(final OrderItemDto orderItemDto);

    @Mapping(target = "id", source = "orderItemDto.id")
    @Mapping(target = "price", source = "orderItemDto.price")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "order", source = "order")
    OrderItem mapToOrderItem(final OrderItemDto orderItemDto, final Order order, final Product product);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItemDto mapToOrderItemDto(final OrderItem orderItem);

    @Mapping(target = "id", source = "orderItem.id")
    @Mapping(target = "price", source = "orderItem.price")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "order", source = "order")
    OrderItemDto mapToOrderItemDto(final OrderItem orderItem, final OrderDto order, final ProductDto product);
    //OrderItemDto mapToOrderItemDto(final OrderItem orderItem, final ProductDto productDto, final OrderDto orderDto);
    //List<OrderItemDto> mapToOrderItemDtoList(final List<OrderItem> orderItemList);

    //List<OrderItem> mapToOrderItemList(final List<OrderItemDto> orderItemDtoList);

}