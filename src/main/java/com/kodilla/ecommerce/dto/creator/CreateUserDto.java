package com.kodilla.ecommerce.dto.creator;

import com.kodilla.ecommerce.domain.enums.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String name;
    private StatusUser status;
    private Long userKey;
}
