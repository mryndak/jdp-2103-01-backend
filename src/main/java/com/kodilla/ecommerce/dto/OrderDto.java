package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.controller.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private OrderItemDto items;
    private String number;
    private StatusOrder status;
    private String shippingAddress;
    private LocalDateTime date;
    private Long userId;
}
