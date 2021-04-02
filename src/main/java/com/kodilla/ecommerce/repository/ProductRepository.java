package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
