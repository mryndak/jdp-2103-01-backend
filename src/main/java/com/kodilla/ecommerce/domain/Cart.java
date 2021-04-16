package com.kodilla.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "carts")
public class Cart {

    @NotNull
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = "cart",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<CartItem> items = new ArrayList<>();

    @Transient
    private BigDecimal totalPrice;
}