package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}