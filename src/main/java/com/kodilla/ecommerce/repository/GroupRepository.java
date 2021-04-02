package com.kodilla.ecommerce.repository;

import com.kodilla.ecommerce.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GroupRepository extends CrudRepository<Group, Long> {}

