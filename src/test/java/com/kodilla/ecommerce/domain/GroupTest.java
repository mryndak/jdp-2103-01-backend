package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GroupTest {

    @Autowired
    private GroupRepository groupRepository;

    Group groupTest = new Group();

    @Test
    public void SaveGroup() {
        //Given
        groupTest.setName("Save group test");

        //When
        groupRepository.save(groupTest);
        Long groupTestId = groupTest.getGroupId();

        //Then
        assertTrue(groupRepository.existsById(groupTestId));

        //CleanUp
        groupRepository.deleteById(groupTestId);
    }

    @Test
    public void SetNameGroup() {
        //Given
        String name = "Test";
        groupTest.setName(name);

        //When
        groupRepository.save(groupTest);

        //Then
        assertEquals(name, groupTest.getName());

        //CleanUp
        groupRepository.deleteAll();
    }
}
