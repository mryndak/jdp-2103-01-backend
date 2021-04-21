package com.kodilla.ecommerce.mapper;

import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.dto.UserDto;
import com.kodilla.ecommerce.dto.creator.CreateUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User mapFromDto(final UserDto userDto);

    @Mapping(target = "cartId", source = "cart.id")
    UserDto mapToDto(final User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User mapFromCreateDto(final CreateUserDto userDto);

}
