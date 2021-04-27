package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/groups")
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value = "This is Group Controller - Here we can do operations on groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{groupId}")
    @ApiOperation(value = "This allows us to get group by id", response = GroupDto.class)
    public GroupDto getGroup(@PathVariable Long groupId)  {
        return groupService.getGroupById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id: " + "dosent exist."));
    }

    @GetMapping("/{groupId}/details")
    @ApiOperation(value = "This allows us to get group with products by an id", response = GroupDto.class)
    public GroupDto getGroupWithProducts(@PathVariable Long groupId)  {
        return groupService.getGroupWithProducts(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id: " + "dosent exist."));
    }

    @GetMapping
    @ApiOperation(value = "This lists our current groups", response = List.class)
    public List<GroupDto> getGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping
    @ApiOperation(value = "This will create a brand new group", response = GroupDto.class)
    public GroupDto createGroup (@RequestBody final GroupDto groupDto) {
        return groupService.saveGroup(groupDto);
    }

    @PutMapping
    @ApiOperation(value = "This allows us to update our group", response = GroupDto.class)
    public GroupDto updateGroup(@RequestBody final GroupDto groupDto) {
        return groupService.updateGroup(groupDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{groupId}")
    @ApiOperation(value = "This allows us to delete our group by providing id")
    public void deleteGroup(@PathVariable Long groupId){
        groupService.deleteGroup(groupId);
    }

}

