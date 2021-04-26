package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.enums.StatusUser;
import com.kodilla.ecommerce.dto.UserDto;
import com.kodilla.ecommerce.dto.creator.CreateUserDto;
import com.kodilla.ecommerce.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value = "This is User Controller - Here we can do operations on users")
public class UsersController {

    private final UserService userService;


    @GetMapping
    @ApiOperation(value = "This lists our current users", response = List.class)
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "This allows us to get user by an id", response = UserDto.class)
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " doesn't exist."));
    }

    @PutMapping("/block")
    @ApiOperation(value = "This allows us to block the user", response = UserDto.class)
    public UserDto blockUser(@RequestBody UserDto userDto){
        userDto.setStatus(StatusUser.BLOCKED_USER);
        return userService.updateUser(userDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/key")
    @ApiOperation(value = "This allows us to generate random user key", response = UserDto.class)
    public UserDto generateUserKey(@RequestBody UserDto userDto){
        Random r = new Random();
        Long userKey = r.nextLong();
        userDto.setUserKey(userKey);
        return userService.updateUser(userDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "This will create brand new user", response = UserDto.class)
    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto userDto) {
        return userService.saveUser(userDto);
    }
}