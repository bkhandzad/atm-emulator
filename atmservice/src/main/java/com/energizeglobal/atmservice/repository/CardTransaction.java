package com.energizeglobal.atmservice.repository;

import com.energizeglobal.atmservice.common.PropertiesCache;
import com.energizeglobal.atmservice.common.CustomHttpEntity;
import com.energizeglobal.atmservice.dto.CurrentCard;
import com.energizeglobal.atmservice.dto.LocalAtmMachine;
import com.energizeglobal.datamodel.CardTransactionDto;
import com.energizeglobal.datamodel.CustomerCardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service(CardTransaction.BEAN_NAME)
public class CardTransaction {
    public static final String BEAN_NAME = "cardTransaction";

    public String validateCard(Long cardNumber){
        RestTemplate restTemplate = new RestTemplate();
        CurrentCard.getINSTANCE().newCard();
        CustomHttpEntity<CustomerCardDto> atmMachine = new CustomHttpEntity<>();
        ResponseEntity<CustomerCardDto> responseEntity = restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.find"), atmMachine, CustomerCardDto.class);
        CurrentCard.getINSTANCE().getCurrentCard().setId(responseEntity.getBody().getId());
        CurrentCard.getINSTANCE().getCurrentCard().setCardNumber(responseEntity.getBody().getCardNumber());
        return responseEntity.getBody().getError().toString();
    }

    public String authenticateCard(String authentication){
        if (CurrentCard.getINSTANCE().getCurrentCard().getCardNumber().toString().length() < 10)
            return "Please Insert Card";
        CurrentCard.getINSTANCE().getCurrentCard().setCardAuthenticationValue(authentication);
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<CustomerCardDto> entity = new CustomHttpEntity<>();
        ResponseEntity<CustomerCardDto> responseEntity = restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.login"), entity.getHttpEntity(CurrentCard.getINSTANCE().getCurrentCard()), CustomerCardDto.class);
        return responseEntity.getBody().getError().toString();
    }

    public CardTransactionDto depositTransaction(BigDecimal amount) {
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<CardTransactionDto> entity = new CustomHttpEntity<>();
        CardTransactionDto transaction = getTransaction(amount);
        return restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.deposit"), entity.getHttpEntity(transaction), CardTransactionDto.class).getBody();
    }

    public CardTransactionDto withdrawTransaction(BigDecimal amount) {
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<CardTransactionDto> entity = new CustomHttpEntity<>();
        CardTransactionDto transaction = getTransaction(amount);
        return restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.withdraw"), entity.getHttpEntity(transaction), CardTransactionDto.class).getBody();
    }

    public void removeTransaction(Long id){
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<Long> entity = new CustomHttpEntity<>();
        restTemplate.delete(PropertiesCache.getInstance().getProperty("service.withdraw"),entity.getHttpEntity(id));
    }

    public CardTransactionDto getTransaction(BigDecimal amount){
        CardTransactionDto transaction = new CardTransactionDto();
        transaction.setTransactionAmount(amount);
        transaction.setAtmMachineDto(LocalAtmMachine.getINSTANCE().getLocalAtm());
        transaction.getCustomerCardDto().setCardNumber(CurrentCard.getINSTANCE().getCurrentCard().getCardNumber());
        return transaction;
    }
}
