package com.energizeglobal.atmservice.repository;

import com.energizeglobal.atmservice.common.PropertiesCache;
import com.energizeglobal.atmservice.common.CustomHttpEntity;
import com.energizeglobal.atmservice.dto.CurrentCard;
import com.energizeglobal.atmservice.dto.LocalAtmMachine;
import com.energizeglobal.datamodel.AtmMachine;
import com.energizeglobal.datamodel.CustomerCard;
import com.energizeglobal.datamodel.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;

@Service(CardTransaction.BEAN_NAME)
public class CardTransaction {
    public static final String BEAN_NAME = "cardTransaction";

    public String validateCard(Long cardNumber){
        RestTemplate restTemplate = new RestTemplate();
        CurrentCard.getINSTANCE().newCard();
        CustomHttpEntity<CustomerCard> atmMachine = new CustomHttpEntity<>();
        ResponseEntity<CustomerCard> responseEntity = restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.find"), atmMachine, CustomerCard.class);
        CurrentCard.getINSTANCE().getCurrentCard().setId(responseEntity.getBody().getId());
        CurrentCard.getINSTANCE().getCurrentCard().setCardNumber(responseEntity.getBody().getCardNumber());
        return responseEntity.getBody().getError().toString();
    }

    public String authenticateCard(String authentication){
        if (CurrentCard.getINSTANCE().getCurrentCard().getCardNumber().toString().length() < 10)
            return "Please Insert Card";
        CurrentCard.getINSTANCE().getCurrentCard().setCardAuthentication(authentication);
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<CustomerCard> entity = new CustomHttpEntity<>();
        ResponseEntity<CustomerCard> responseEntity = restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.login"), entity.getHttpEntity(CurrentCard.getINSTANCE().getCurrentCard()), CustomerCard.class);
        return responseEntity.getBody().getError().toString();
    }

    public Transaction depositTransaction(BigDecimal amount) {
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<Transaction> entity = new CustomHttpEntity<>();
        Transaction transaction = getTransaction(amount);
        return restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.deposit"), entity.getHttpEntity(transaction), Transaction.class).getBody();
    }

    public Transaction withdrawTransaction(BigDecimal amount) {
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<Transaction> entity = new CustomHttpEntity<>();
        Transaction transaction = getTransaction(amount);
        return restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.withdraw"), entity.getHttpEntity(transaction), Transaction.class).getBody();
    }

    public void removeTransaction(Long id){
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<Long> entity = new CustomHttpEntity<>();
        restTemplate.delete(PropertiesCache.getInstance().getProperty("service.withdraw"),entity.getHttpEntity(id));
    }

    public Transaction getTransaction(BigDecimal amount){
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAtmMachine(LocalAtmMachine.getINSTANCE().getLocalAtm());
        transaction.setCardNumber(CurrentCard.getINSTANCE().getCurrentCard().getCardNumber());
        return transaction;
    }
}
