package com.kodilla.ecommerce.controller;

import com.kodilla.ecommerce.controller.request.CartToOrderDto;
import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.dto.OrderItemDto;
import com.kodilla.ecommerce.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/orders", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value = "This is Order Controller - Here we can do operations on the order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @ApiOperation(value = "This lists our current orders", response = List.class)
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping ("/{id}")
    @ApiOperation(value = "This allows to get an order by an id", response = OrderDto.class)
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " doesn't exist."));
    }

    @PostMapping
    @ApiOperation(value = "This will create a brand new order", response = OrderDto.class)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    @PostMapping("/fromCart")
    @ApiOperation(value = "Create an order from the cart", response = OrderDto.class)
    public OrderDto createOrderFromCart(@RequestBody CartToOrderDto cartToOrderDto){
        return orderService.saveOrderFromCart(cartToOrderDto);
    }

    @PutMapping
    @ApiOperation(value = "This allows to update an order", response = OrderDto.class)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation(value = "This allows us to delete our order by providing id")
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }
}
