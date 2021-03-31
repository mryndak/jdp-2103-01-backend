package com.kodilla.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String number;
    private String status;
    private LocalDateTime date;
    private Long userId;
    private Long productId;
}
