package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "products", ignore = true)
    Group mapToGroup(final GroupDto groupDto);

    @Mapping(target = "products", ignore = true)
    GroupDto mapToGroupDto(final Group group);


    List<GroupDto> mapToGroupDtoList(final List<Group> groupList);

}