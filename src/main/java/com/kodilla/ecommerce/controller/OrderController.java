package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.controller.enums.StatusOrder;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/orders", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders() {
        List<Product> productsList = new ArrayList<>();
        Product product = new Product();
            product.setId(1L);
            product.setName("kurtka zimowa");
            product.setDescription("");
            product.setPrice(new BigDecimal(100));
            product.setGroupId(1L);
        productsList.add(product);

        return Arrays.asList(OrderDto.builder()
                .id(1L)
                .number("1")
                .status(StatusOrder.IN_PROGRESS)
                .totalPrice(new BigDecimal(100))
                .shippingAddress("81-155 Gdynia, Kościuszki 1/2")
                .date(LocalDateTime.of(2021, 3, 1, 23, 23, 15))
                .products(productsList)
                .userId(1L)
                .build());
    }

    @GetMapping ("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return OrderDto.builder()
                .id(1L)
                .number("1")
                .status(StatusOrder.ACCEPTED)
                .date(LocalDateTime.now())
                .build();
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return OrderDto.builder()
                .id(1L)
                .number("1")
                .status(StatusOrder.ACCEPTED)
                .date(LocalDateTime.now())
                .build();
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return OrderDto.builder()
                .id(1L)
                .number("1")
                .status(StatusOrder.CANCELED)
                .date(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
    }
}
