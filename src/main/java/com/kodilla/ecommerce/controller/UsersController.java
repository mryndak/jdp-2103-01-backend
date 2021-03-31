package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ExampleDto;
import com.kodilla.ecommerce.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UsersController {

    @GetMapping
    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return new UserDto(1L,"Test User", "1", 10000L);
    }

    @PutMapping
    public void blockUser(@RequestBody UserDto userDto){
        userDto.setStatus("0");
    }

    @PostMapping(value = "createUser")
    public void createUser(@RequestBody UserDto userDto) {

    }




}