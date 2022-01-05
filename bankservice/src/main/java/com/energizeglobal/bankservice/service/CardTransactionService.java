package com.energizeglobal.bankservice.service;

import com.energizeglobal.bankservice.domain.CardTransactionEntity;
import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import com.energizeglobal.datamodel.request.CardBalanceDto;
import com.energizeglobal.datamodel.types.CardTransactionResult;
import com.energizeglobal.datamodel.types.TransactionType;
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

    public CardTransactionDto deposit(CardTransactionDto transaction){
        CardTransactionEntity entity = getCardTransaction(transaction.getCardNumber(),transaction.getAtmMachineDto().getId(),transaction.getTransactionAmount());
        entity.setTransactionType(TransactionType.DEPOSIT);
        cardTransactionRepository.save(entity);
        transaction.setId(entity.getId());
        transaction.setInsertDate(entity.getInsertDate());
        transaction.setTransactionResult(CardTransactionResult.OK);
        return transaction;
    }

    public CardTransactionDto withdraw(CardTransactionDto transaction){
        CardBalanceDto balanceDto = getCardBalance(new CardBalanceDto(transaction.getCardNumber()));
        if (transaction.getTransactionAmount().compareTo(balanceDto.getBalance()) > 0){
            transaction.setTransactionResult(CardTransactionResult.INSUFFICIENT_BALANCE);
            return transaction;
        }
        CardTransactionEntity entity = getCardTransaction(transaction.getCardNumber(),transaction.getAtmMachineDto().getId(),transaction.getTransactionAmount());
        entity.setTransactionType(TransactionType.WITHDRAW);
        cardTransactionRepository.save(entity);
        transaction.setId(entity.getId());
        transaction.setInsertDate(entity.getInsertDate());
        transaction.setTransactionResult(CardTransactionResult.OK);
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

    public List<CardTransactionDto> getLastTransactions(Long cardNumber,Integer count) {
        List<CardTransactionDto> transactions = new ArrayList<>();
        List<CardTransactionEntity> cardTransactionEntities = cardTransactionRepository.findAllByCustomerCardEntityOrderByInsertDate(customerCardService.findCustomerCardEntity(cardNumber));
        for (Integer i = 0; i < Math.min(count, cardTransactionEntities.size()); i++)
            transactions.add(cardTransactionTransformer.createValueObjectFromEntity(cardTransactionEntities.get(i)));
        return transactions;
    }

    public CardBalanceDto getCardBalance(CardBalanceDto cardBalanceDto){
        CustomerCardEntity customerCardEntity = customerCardService.findCustomerCardEntity(cardBalanceDto.getCardNumber());
        BigDecimal balance = cardTransactionRepository.sumDepositAmount(customerCardEntity).subtract(cardTransactionRepository.sumWithdrawAmount(customerCardEntity));
        cardBalanceDto.setBalance(balance);
        return cardBalanceDto;
    }

    public void deleteById(Long id){
        cardTransactionRepository.deleteById(id);
    }
}
