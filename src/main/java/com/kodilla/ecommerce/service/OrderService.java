package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.mapper.OrderItemMapper;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    //public List<OrderDto> getOrders() {
    //return orderMapper.mapToOrderDtoList(orderRepository.findAll());
}