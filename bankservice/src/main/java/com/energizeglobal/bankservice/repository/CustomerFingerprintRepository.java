package com.energizeglobal.bankservice.repository;

import com.energizeglobal.bankservice.domain.CustomerEntity;
import com.energizeglobal.bankservice.domain.CustomerFingerprintEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerFingerprintRepository extends CrudRepository<CustomerFingerprintEntity,Long> {

    Optional<CustomerFingerprintEntity> findByFingerprint(String fingerprint);

    Optional<CustomerFingerprintEntity> findByCustomerEntity(CustomerEntity customerEntity);
}
