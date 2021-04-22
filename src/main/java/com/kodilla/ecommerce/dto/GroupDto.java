package com.kodilla.ecommerce.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupDto {

    private Long groupId;
    private String name;
    @Builder.Default
    private List<ProductDto> products = new ArrayList<>();

}