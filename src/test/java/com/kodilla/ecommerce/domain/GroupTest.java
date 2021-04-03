package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class GroupTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testSaveGroup() {

        //Given
        Group groupTest1 = new Group();
        groupTest1.setName("Odzież");
        //When
        groupRepository.save(groupTest1);
        Long groupTest1Id = groupTest1.getGroupId();
        Optional<Group> testGroupId = groupRepository.findById(groupTest1Id);
        //Then
        assertTrue(groupRepository.existsById(groupTest1Id));
        assertTrue(testGroupId.isPresent());
        //Clean-up
        groupRepository.deleteById(groupTest1Id);
    }

    @Test
    public void testSetNameGroup() {
        //Given
        Group groupTest2 = new Group();
        groupTest2.setName("Obuwie");
        //When
        groupRepository.save(groupTest2);
        String name = groupTest2.getName();
        //Then
        assertEquals("Obuwie", name);
        //CleanUp
        groupRepository.deleteAll();
    }

}