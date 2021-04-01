package com.kodilla.ecommerce.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(name = "name")
    private String name;

}
