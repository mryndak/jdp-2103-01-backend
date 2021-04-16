package com.kodilla.ecommerce.dto;

import com.kodilla.ecommerce.domain.enums.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private StatusUser status;
    private Long userKey;
    @Builder.Default
    private List<OrderDto> orders = new ArrayList<>();
    private Long cartId;
}