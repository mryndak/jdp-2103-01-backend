package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "group", ignore = true)
    Product mapToProduct(ProductDto productDto);

    @Mapping(target = "groupId", source = "group.groupId")
    ProductDto mapToProductDto(Product product);
}
