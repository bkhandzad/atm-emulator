package com.energizeglobal.bankservice.repository;

import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerCardRepository extends CrudRepository<CustomerCardEntity,Long> {

    Optional<CustomerCardEntity> findByCardNumber(Long cardNumber);
}
