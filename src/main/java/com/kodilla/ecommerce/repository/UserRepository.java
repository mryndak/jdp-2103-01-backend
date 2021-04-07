package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
