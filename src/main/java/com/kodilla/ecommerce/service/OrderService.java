package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.dto.OrderItemDto;
import com.kodilla.ecommerce.mapper.OrderItemMapper;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.mapper.UserMapper;
import com.kodilla.ecommerce.repository.OrderItemRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserMapper userMapper;

    public List<OrderDto> getOrders() {
        return orderRepository.findAll(Sort.by("id"))
                .stream()
                .map(orderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public Optional<OrderDto> getOrderById(final Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::mapToOrderDto);
    }

    @SneakyThrows
    public OrderDto saveOrder(final OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Optional<User> user = userRepository.findById(orderDto.getUserId());
        if (user.isPresent()) {
            order.setUser(user.get());
        } else {
            throw new NotFoundException("User doesn't exist, please change the user.");
        }
        List<OrderItem> items = orderItemRepository.findByOrder(order);
        List<OrderItemDto> itemsDto = items.stream()
                .map(orderItemMapper::mapToOrderItemDto)
                .collect(Collectors.toList());
        order.setItems(items);
        order = orderRepository.save(order);
        return orderMapper.mapToOrderDto(order, itemsDto);
    }

    @SneakyThrows
    public Optional<OrderDto> updateOrder(final OrderDto orderDto) {
        Optional<Order> order = orderRepository.findById(orderDto.getId());
        if (order.isPresent()) {
            order.ifPresent(o -> {
                o.setStatus(orderDto.getStatus());
                o.setId(orderDto.getId());
            });
            return order.map(orderMapper::mapToOrderDto);
        }
        return Optional.empty();
    }

    public void deleteOrderById(final Long id) {
        orderRepository.deleteById(id);
    }
}