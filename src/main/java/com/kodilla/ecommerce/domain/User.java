package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.controller.enums.StatusUser;
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
@Table(name = "users")
public class User {

    @NotNull
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String username;

    @Column(name = "status")
    private StatusUser status;

    @Column(name = "key")
    private Long userKey;



}
