package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.domain.enums.StatusOrder;
import com.kodilla.ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderTestSuite {
    @Autowired
    private OrderRepository orderRepository;
    private final Product productTest = new Product();
    private final Group groupTest = new Group();
    private final OrderItem orderItemTest = new OrderItem();
    private final List<OrderItem> orderItemListTest = new ArrayList<>();
    private final Order orderTest = new Order();

    @Test
    void testSaveOrder() {
        //Given
        groupTest.setName("Test - group name");

        productTest.setName("Test - product name");
        productTest.setPrice(new BigDecimal(10));
        productTest.setDescription("Test - description");
        productTest.setGroup(groupTest);

        orderItemTest.setQuantity(1);
        orderItemTest.setPrice(new BigDecimal(10));
        orderItemTest.setProduct(productTest);
        orderItemTest.setOrder(orderTest);

        orderItemListTest.add(orderItemTest);

        orderTest.setItems(orderItemListTest);
        orderTest.setNumber("123");
        orderTest.setStatus(StatusOrder.ACCEPTED);
        orderTest.setShippingAddress("Test - shipping address");
        orderTest.setDateOfCreation(LocalDateTime.now());
        orderTest.setUserId(1L);
        //When
        orderRepository.save(orderTest);
        Long orderTestId = orderTest.getId();

        try {
            //Then
            Optional<Order> readOrderId = orderRepository.findById(orderTestId);
            assertTrue(readOrderId.isPresent());
            assertNotEquals(StatusOrder.IN_PROGRESS, orderTest.getStatus());
        } finally {
            //CleanUp
            orderRepository.deleteById(orderTestId);
        }
    }

    @Test
    void testUpdateOrder() {
        //Given
        groupTest.setName("Test - group name");

        productTest.setName("Test - product name");
        productTest.setPrice(new BigDecimal(10));
        productTest.setDescription("Test - description");
        productTest.setGroup(groupTest);

        orderItemTest.setQuantity(1);
        orderItemTest.setPrice(new BigDecimal(10));
        orderItemTest.setProduct(productTest);
        orderItemTest.setOrder(orderTest);

        orderItemListTest.add(orderItemTest);

        orderTest.setItems(orderItemListTest);
        orderTest.setNumber("123");
        orderTest.setStatus(StatusOrder.ACCEPTED);
        orderTest.setShippingAddress("Test - shipping address");
        orderTest.setDateOfCreation(LocalDateTime.now());
        orderTest.setUserId(1L);
        //When
        orderTest.setStatus(StatusOrder.IN_PROGRESS);
        orderTest.setUserId(2L);
        orderTest.setShippingAddress("Test - update shipping address");
        orderRepository.save(orderTest);
        Long orderTestId = orderTest.getId();

        try {
            //Then
            Optional<Order> readOrderId = orderRepository.findById(orderTestId);
            assertTrue(readOrderId.isPresent());
            assertEquals(StatusOrder.IN_PROGRESS, orderTest.getStatus());
            assertEquals(2L, orderTest.getUserId());
            assertEquals("Test - update shipping address", orderTest.getShippingAddress());
        } finally {
            //CleanUp
            orderRepository.deleteById(orderTestId);
        }
    }

    @Test
    void testDeleteOrder() {
        //Given
        groupTest.setName("Test - group name");

        productTest.setName("Test - product name");
        productTest.setPrice(new BigDecimal(10));
        productTest.setDescription("Test - description");
        productTest.setGroup(groupTest);

        orderItemTest.setQuantity(1);
        orderItemTest.setPrice(new BigDecimal(10));
        orderItemTest.setProduct(productTest);
        orderItemTest.setOrder(orderTest);

        orderItemListTest.add(orderItemTest);

        orderTest.setItems(orderItemListTest);
        orderTest.setNumber("123");
        orderTest.setStatus(StatusOrder.ACCEPTED);
        orderTest.setShippingAddress("Test - shipping address");
        orderTest.setDateOfCreation(LocalDateTime.now());
        orderTest.setUserId(1L);

        orderRepository.save(orderTest);
        //When
        Long orderTestId = orderTest.getId();
        orderRepository.deleteById(orderTestId);
        Optional<Order> readOrder = orderRepository.findById(orderTestId);
        //Then
        assertTrue(readOrder.isEmpty());
    }
}