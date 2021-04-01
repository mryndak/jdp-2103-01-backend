package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.ExampleDto;
import com.kodilla.ecommerce.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class UsersController {

    @GetMapping
    public List<UserDto> getUsers() {
        return Arrays.asList(
                UserDto.builder()
                        .id(1L)
                        .username("Test User")
                        .status(UserDto.Status.ACTIVE_USER)
                        .userKey(10000L)
                        .build()
        );
    }

    @GetMapping(value = "/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return UserDto.builder()
                .id(1L)
                .username("Test User")
                .status(UserDto.Status.ACTIVE_USER)
                .userKey(10000L)
                .build();
    }

    @PutMapping(value = "blockUser")
    public void blockUser(@RequestBody UserDto userDto){
        userDto.setStatus(UserDto.Status.BLOCKED_USER);
    }

    @PutMapping(value = "generateUserKey")
    public void generateUserKey(@RequestBody UserDto userDto){
        userDto.setUserKey(10001L);
    }

    @PostMapping(value = "createUser")
    public void createUser(@RequestBody UserDto userDto) {

    }






}