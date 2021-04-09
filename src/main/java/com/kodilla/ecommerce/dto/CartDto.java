package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartDto {

    @Builder.Default
    private List<CartItem> items = new ArrayList<>();
    private Long id;
    private Long userId;
}