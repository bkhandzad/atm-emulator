package com.energizeglobal.bankservice.repository;

import com.energizeglobal.bankservice.domain.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByNationalId(Long nationalId);
}
