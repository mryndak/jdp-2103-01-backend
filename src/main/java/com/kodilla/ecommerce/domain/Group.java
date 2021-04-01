package com.kodilla.ecommerce.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "groups")
public class Group {

    @NotNull
    @Id
    @GeneratedValue
    private Long groupId;

    @NotNull
    @Size(min = 4, max = 50)
    private String name;

}
