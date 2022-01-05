package com.energizeglobal.atmservice.repository;

import com.energizeglobal.atmservice.common.PropertiesCache;
import com.energizeglobal.atmservice.dto.CurrentCard;
import com.energizeglobal.datamodel.request.CardDataRequestDto;
import com.energizeglobal.datamodel.types.CurrentCardSate;
import com.energizeglobal.infrastructure.CustomHttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service(CustomerCard.BEAN_NAME)
public class CustomerCard {
    public static final String BEAN_NAME = "customerCard";

    public String validateCard(Long cardNumber){
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<CardDataRequestDto> data = new CustomHttpEntity<>();
        CurrentCard.getINSTANCE().setCardNumber(cardNumber);
        ResponseEntity<CardDataRequestDto> responseEntity = restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.find"), data.getHttpEntity(CurrentCard.getINSTANCE().getCardDataRequestDto()), CardDataRequestDto.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            CurrentCard.getINSTANCE().setId(responseEntity.getBody().getId());
            CurrentCard.getINSTANCE().setCardNumber(responseEntity.getBody().getCardNumber());
            CurrentCard.getINSTANCE().setCurrentCardSate(CurrentCardSate.CARD_INSERTED);
            return responseEntity.getBody().getError().toString();
        }
        else
            return "Server Error";
    }

    public String authenticateCard(String authentication) {
        RestTemplate restTemplate = new RestTemplate();
        CustomHttpEntity<CardDataRequestDto> entity = new CustomHttpEntity<>();
        ResponseEntity<CardDataRequestDto> responseEntity = restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.validate"), entity.getHttpEntity(CurrentCard.getINSTANCE().getCardDataRequestDto()), CardDataRequestDto.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            CurrentCard.getINSTANCE().setCurrentCardSate(CurrentCardSate.CARD_VALIDATED);
            return responseEntity.getBody().getError().toString();
        }
        else
            return "Server Error";
    }
}
