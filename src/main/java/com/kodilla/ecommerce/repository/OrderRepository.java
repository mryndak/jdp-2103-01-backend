package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Order;
import com.kodilla.ecommerce.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUser(User user);
}