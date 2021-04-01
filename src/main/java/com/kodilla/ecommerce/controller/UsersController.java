package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.enums.StatusUser;
import com.kodilla.ecommerce.dto.UserDto;
import org.springframework.web.bind.annotation.*;

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
                        .status(StatusUser.ACTIVE_USER)
                        .userKey(10000L)
                        .build()
        );
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return UserDto.builder()
                .id(1L)
                .username("Test User")
                .status(StatusUser.ACTIVE_USER)
                .userKey(10000L)
                .build();
    }

    @PutMapping("blockUser")
    public void blockUser(@RequestBody UserDto userDto){
        userDto.setStatus(StatusUser.BLOCKED_USER);
    }

    @PutMapping("generateUserKey")
    public void generateUserKey(@RequestBody UserDto userDto){
        userDto.setUserKey(10001L);
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {

    }






}