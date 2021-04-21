package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Group;
import com.kodilla.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByGroup(Group group);
}