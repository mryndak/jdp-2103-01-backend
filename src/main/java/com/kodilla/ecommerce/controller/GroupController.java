package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.service.GroupService;
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
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId)  {
        return groupService.getGroupById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id: " + "dosent exist."));
    }

    @GetMapping("/{groupId}/details")
    public GroupDto getGroupWithProducts(@PathVariable Long groupId)  {
        return groupService.getGroupWithProducts(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id: " + "dosent exist."));
    }

    @GetMapping
    public List<GroupDto> getGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping
    public GroupDto createGroup (@RequestBody final GroupDto groupDto) {
        return groupService.saveGroup(groupDto);
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody final GroupDto groupDto) {
        return groupService.updateGroup(groupDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable Long groupId){
        groupService.deleteGroup(groupId);
    }

}

