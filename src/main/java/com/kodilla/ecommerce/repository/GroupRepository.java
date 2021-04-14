package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Override
    List<Group> findAll();

    @Override
    Optional<Group> findById(Long id);

}