package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.domain.enums.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    private StatusUser status;

    @NotNull
    private Long userKey;

    @Builder.Default
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.REFRESH,
            fetch = FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", unique = true)
    private Cart cart;

    private String email;
}