package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "products", ignore = true)
    Group mapToGroup(final GroupDto groupDto);

//    @Mapping(target = "products", ignore = true)
    Group mapToGroup(final GroupDto groupDto, final List<Product> products);

    @Mapping(target = "products", ignore = true)
    GroupDto mapToGroupDto(final Group group);

    GroupDto mapToGroupDto(final Group group, final List<ProductDto> products);

    List<GroupDto> mapToGroupDtoList(final List<Group> groupList);

}