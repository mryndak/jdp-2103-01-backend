package com.kodilla.ecommerce.service;

import com.kodilla.ecommerce.controller.EmailController;
import com.kodilla.ecommerce.controller.request.CartToOrderDto;
import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.domain.OrderItem;
import com.kodilla.ecommerce.domain.Product;
import com.kodilla.ecommerce.domain.User;
import com.kodilla.ecommerce.dto.OrderDto;
import com.kodilla.ecommerce.dto.OrderItemDto;
import com.kodilla.ecommerce.mapper.OrderItemMapper;
import com.kodilla.ecommerce.mapper.OrderMapper;
import com.kodilla.ecommerce.mapper.ProductMapper;
import com.kodilla.ecommerce.mapper.UserMapper;
import com.kodilla.ecommerce.repository.OrderItemRepository;
import com.kodilla.ecommerce.repository.OrderRepository;
import com.kodilla.ecommerce.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    private final EmailController emailController;

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
    public OrderDto saveOrderFromCart(final CartToOrderDto cartToOrderDto) {

        OrderDto orderDto = OrderDto.builder()
                .id(cartToOrderDto.getId())
                .userId(cartToOrderDto.getCartDto().getUserId())
                .number(cartToOrderDto.getNumber())
                .status(cartToOrderDto.getStatus())
                .shippingAddress(cartToOrderDto.getShippingAddress())
                .build();

        List<OrderItemDto> orderItemsDto = cartToOrderDto.getCartDto().getItems().stream()
                .map(item -> {
                    Optional<Product> product = productRepository.findById(item.getProductId());
                    if (product.isPresent()) {
                        OrderItemDto orderItemDto = OrderItemDto.builder()
                                .product(productMapper.mapToProductDto(productRepository.findById(item.getProductId()).get()))
                                .quantity(item.getQuantity())
                                .order(orderDto)
                                .build();
                        return orderItemDto;
                    }else {
                        return null;
                    }
                })
                .collect(Collectors.toList());

        orderDto.setItems(orderItemsDto);
        final Order orderToSave = orderMapper.mapToOrder(orderDto);
        Optional<User> user = userRepository.findById(orderDto.getUserId());

        if (user.isPresent()) {
            orderToSave.setUser(user.get());
        } else {
            throw new NotFoundException("User doesn't exist, please change the user.");
        }

        List<OrderItem> items = orderItemsDto.stream()
                .map(item -> orderItemMapper.mapToOrderItem(item,orderToSave, productMapper.mapToProduct(item.getProduct())))
                .collect(Collectors.toList());

        orderToSave.setItems(items);
        Order order = orderRepository.save(orderToSave);
        return orderMapper.mapToOrderDto(order, orderItemsDto);
    }

    @SneakyThrows
    public Optional<OrderDto> updateOrder(final OrderDto orderDto) {
        Optional<Order> order = orderRepository.findById(orderDto.getId());
        if (order.isPresent()) {
            order.ifPresent(o -> {
                o.setStatus(orderDto.getStatus());
                emailController.sendEmail(o.getUser(), o);
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