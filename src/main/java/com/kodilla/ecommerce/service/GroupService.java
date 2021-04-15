package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.GroupDto;
import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.mapper.GroupMapper;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.repository.GroupRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

   private final GroupRepository groupRepository;
   private final ProductRepository productRepository;
   private final GroupMapper groupMapper;
   private final ProductMapper productMapper;

    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll(Sort.by("name"))
                .stream()
                .map(groupMapper::mapToGroupDto)
                .collect(Collectors.toList());
    }

    public Optional<GroupDto> getGroupById(final Long id) {
        return groupRepository.findById(id)
                .map(groupMapper::mapToGroupDto);
    }

    public Optional<GroupDto> getGroupWithProducts(final Long id) {
        final Optional<Group> groupOptional = groupRepository.findById(id);
        if(groupOptional.isPresent()) {
            final Group group = groupOptional.get();
            final List<ProductDto> products = productRepository.findByGroup(group)
                    .stream()
                    .map(productMapper::mapToDto)
                    .collect(Collectors.toList());
            final GroupDto groupDto = groupMapper.mapToGroupDto(group);
            groupDto.setProducts(products);
            return Optional.of(groupDto);
        }
        return Optional.empty();
    }

    public GroupDto saveGroup(final GroupDto group) {
        Group groupToSave = groupMapper.mapToGroup(group);
        groupToSave = groupRepository.save(groupToSave);
        return groupMapper.mapToGroupDto(groupToSave);
    }

    public Optional<GroupDto> updateGroup(final GroupDto groupDto) {
        Optional<Group> group = groupRepository.findById(groupDto.getGroupId());
        if(group.isPresent()) {
            group.ifPresent(g -> g.setName(groupDto.getName()));
            return group.map(groupMapper::mapToGroupDto);
        }
        return Optional.empty();
    }
}
