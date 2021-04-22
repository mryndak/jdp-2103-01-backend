package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.domain.enums.StatusOrder;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.dto.OrderItemDto;
import com.kodilla.ecommerce.dto.ProductDto;
import com.kodilla.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/orders", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping ("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return OrderDto.builder()
                .id(1L)
                .number("1")
                .status(StatusOrder.ACCEPTED)
                //.date(LocalDateTime.now())
                .build();
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return OrderDto.builder()
                .id(1L)
                .number("1")
                .status(StatusOrder.CANCELED)
                //.date(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
    }
}