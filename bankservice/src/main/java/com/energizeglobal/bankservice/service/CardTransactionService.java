package com.energizeglobal.bankservice.service;

import com.energizeglobal.bankservice.domain.CardTransactionEntity;
import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import com.energizeglobal.bankservice.domain.types.TransactionType;
import com.energizeglobal.bankservice.repository.CardTransactionRepository;
import com.energizeglobal.bankservice.transformer.CardTransactionTransformer;
import com.energizeglobal.datamodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service(CardTransactionService.BEAN_NAME)
public class CardTransactionService {
    public static final String BEAN_NAME = "cardTransactionService";

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private CardTransactionTransformer cardTransactionTransformer;

    @Autowired
    private AtmMachineService atmMachineService;

    @Autowired
    private CustomerCardService customerCardService;

    public Transaction deposit(Transaction transaction){
        CardTransactionEntity entity = getCardTransaction(transaction.getCardNumber(),transaction.getAtmMachine().getId(),transaction.getAmount());
        entity.setTransactionType(TransactionType.DEPOSIT);
        cardTransactionRepository.save(entity);
        transaction.setId(entity.getId());
        transaction.setTransactionTime(entity.getInsertDate());
        return transaction;
    }

    public Transaction withdraw(Transaction transaction){
        CardTransactionEntity entity = getCardTransaction(transaction.getCardNumber(),transaction.getAtmMachine().getId(),transaction.getAmount());
        entity.setTransactionType(TransactionType.WITHDRAW);
        cardTransactionRepository.save(entity);
        transaction.setId(entity.getId());
        transaction.setTransactionTime(entity.getInsertDate());
        return transaction;
    }

    public CardTransactionEntity getCardTransaction(Long cardNumber,Long atmMachineId,BigDecimal amount){
        CardTransactionEntity entity = new CardTransactionEntity();
        entity.setCustomerCardEntity(customerCardService.findCustomerCardEntity(cardNumber));
        entity.setAtmMachineEntity(atmMachineService.findByID(atmMachineId));
        entity.setTransactionAmount(amount);
        entity.setInsertDate(LocalDateTime.now());
        return entity;
    }

    public List<Transaction> getLastTransactions(Long cardNumber,Integer count) {
        List<Transaction> transactions = new ArrayList<>();
        List<CardTransactionEntity> cardTransactionEntities = cardTransactionRepository.findAllByCustomerCardEntityOrderByInsertDate(customerCardService.findCustomerCardEntity(cardNumber));
        for (Integer i = 0; i < Math.min(count, cardTransactionEntities.size()); i++) {
            AtmMachine atmMachine = new AtmMachine(cardTransactionEntities.get(i).getAtmMachineEntity().getId(), cardTransactionEntities.get(i).getAtmMachineEntity().getUsername());
            Transaction transaction = new Transaction(cardTransactionEntities.get(i).getId(), cardTransactionEntities.get(i).getTransactionAmount(),
                    cardTransactionEntities.get(i).getCustomerCardEntity().getCardNumber(), cardTransactionEntities.get(i).getInsertDate(),
                    cardTransactionEntities.get(i).getTransactionType().name(), atmMachine);
            transactions.add(transaction);
        }
        return transactions;
    }

    public CardBalanceVo getCardBalance(Long cardNumber){
        CustomerCardEntity customerCardEntity = customerCardService.findCustomerCardEntity(cardNumber);
        BigDecimal balance = cardTransactionRepository.sumDepositAmount(customerCardEntity).subtract(cardTransactionRepository.sumWithdrawAmount(customerCardEntity));
        CardBalanceVo cardBalanceVo = new CardBalanceVo(balance,customerCardEntity.getCardNumber());
        return cardBalanceVo;
    }

    public void deleteById(Long id){
        cardTransactionRepository.deleteById(id);
    }
}
