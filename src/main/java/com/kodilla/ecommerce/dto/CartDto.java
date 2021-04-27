package com.kodilla.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartDto {

    private Long id;
    private Long userId;
    @Builder.Default
    private List<CartItemDto> items = new ArrayList<>();
    private BigDecimal totalPrice;
}