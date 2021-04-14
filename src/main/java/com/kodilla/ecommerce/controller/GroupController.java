package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.mapper.GroupMapper;
import com.kodilla.ecommerce.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/group", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class GroupController {

    private GroupService groupService;
    private GroupMapper groupMapper;

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) throws GroupNotFoundException{
        return groupMapper.mapToGroupDto(groupService.getGroupById(groupId).orElseThrow(GroupNotFoundException::new));
    }

    @GetMapping
    public List<GroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }

    @PostMapping
    public void createGroup (@RequestBody final GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupService.saveGroup(group);
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody final GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group savedGroup = groupService.saveGroup(group);
        return groupMapper.mapToGroupDto(savedGroup);
    }

}

