package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/order", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OrderController {

    @GetMapping(value = "orders")
    public List<OrderDto> getOrders() {
        List<OrderDto> orders = new ArrayList<>();
        orders.add(new OrderDto(1L, "123","1", LocalDateTime.of(2021, 3, 30, 6, 30, 50), 1L, 1L));
        orders.add(new OrderDto(2L, "124", "0", LocalDateTime.of(2021, 3, 30, 8, 30, 23), 1L, 1L));
        return orders;
    }

    @GetMapping
    public OrderDto getOrder(@RequestParam Long id) {
        return new OrderDto(1L, "12345", "1", LocalDateTime.now(), 1L, 1L);
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return new OrderDto(1L, "12345", "1", LocalDateTime.now(), 1L, 1L);
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return new OrderDto(1L, "12346", "1", LocalDateTime.now(), 1L, 1L);
    }

    @DeleteMapping
    public void deleteOrder(@RequestParam Long id) {
    }
}
