package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.controller.enums.StatusOrder;
import com.kodilla.ecommerce.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String number;
    private StatusOrder status;
    private BigDecimal totalPrice;
    private String shippingAddress;
    private LocalDateTime date;
    private List<Product> products;
    private Long userId;
}
