package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
