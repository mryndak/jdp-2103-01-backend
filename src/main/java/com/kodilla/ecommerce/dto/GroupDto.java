package com.kodilla.ecommerce.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupDto {

    private Long groupId;
    private String name;
}