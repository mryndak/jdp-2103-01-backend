package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private List<OrderItemDto> items;
    private String number;
    private StatusOrder status;
    private String shippingAddress;
    private LocalDateTime date;
    private Long userId;
}
