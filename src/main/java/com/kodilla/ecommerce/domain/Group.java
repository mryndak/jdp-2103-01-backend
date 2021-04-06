package com.kodilla.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "products_groups")
public class Group {

    @NotNull
    @Id
    @GeneratedValue
    private Long groupId;

    @NotNull
    @Size(min = 4, max = 50)
    private String name;

}
