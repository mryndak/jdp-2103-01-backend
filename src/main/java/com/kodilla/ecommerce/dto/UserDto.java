package com.kodilla.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private Status status;
    private Long userKey;

    public enum Status{BLOCKED_USER, ACTIVE_USER};
}
