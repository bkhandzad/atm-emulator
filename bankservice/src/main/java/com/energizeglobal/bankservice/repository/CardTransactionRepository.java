package com.energizeglobal.bankservice.repository;

import com.energizeglobal.bankservice.domain.CardTransactionEntity;
import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardTransactionRepository extends CrudRepository<CardTransactionEntity,Long> {
    @Query(value = "SELECT sum(c.transactionAmount) FROM CardTransactionEntity c WHERE c.transactionType = -1 AND c.customerCardEntity = :customerCardEntity")
    BigDecimal sumWithdrawAmount(CustomerCardEntity customerCardEntity);

    @Query(value = "SELECT sum(c.transactionAmount) FROM CardTransactionEntity c WHERE c.transactionType = 1 AND c.customerCardEntity = :customerCardEntity")
    BigDecimal sumDepositAmount(CustomerCardEntity customerCardEntity);

    List<CardTransactionEntity> findAllByCustomerCardEntityOrderByInsertDate(CustomerCardEntity customerCardEntity);
}
