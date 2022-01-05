package com.energizeglobal.atmservice.service;

import com.energizeglobal.atmservice.common.CurrentCardSate;
import com.energizeglobal.atmservice.dto.CurrentCard;
import com.energizeglobal.atmservice.repository.CardTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CustomerCardService.BEAN_NAME)
public class CustomerCardService {
    public static final String BEAN_NAME = "customerCardService";

    @Autowired
    CardTransaction transaction;

    public String validateCard(Long cardNumber){
        return transaction.validateCard(cardNumber);
    }

    public String authenticateCard(String authentication){
        if (CurrentCard.getINSTANCE().getCurrentCardSate() == CurrentCardSate.NONE)
            return "Please Insert Card";
        return transaction.authenticateCard(authentication);
    }
}
