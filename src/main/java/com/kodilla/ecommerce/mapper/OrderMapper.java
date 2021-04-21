package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.criteria.Order;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "product.group", ignore = true)
    @Mapping(target = "id", ignore = true)

    @Mapping(target = "items[].product.groupId", ignore = true)
    OrderDto mapToOrderDto(final Order order, final List<OrderItem> items);

    List<OrderDto> mapToOrderDtoList(final List<OrderItem> items);

}