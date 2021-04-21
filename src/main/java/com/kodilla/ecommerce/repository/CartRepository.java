package com.kodilla.ecommerce.repository;


import com.kodilla.ecommerce.domain.Cart;
import com.kodilla.ecommerce.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByUser(User User);
}