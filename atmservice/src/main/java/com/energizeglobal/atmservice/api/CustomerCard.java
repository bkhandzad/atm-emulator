package com.energizeglobal.atmservice.api;

import com.energizeglobal.atmservice.common.CurrentCardSate;
import com.energizeglobal.atmservice.dto.CurrentCard;
import com.energizeglobal.atmservice.repository.CardTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/card")
public class CustomerCard {
    @Autowired
    CardTransaction transaction;

    @GetMapping(value = "/validateCard")
    public String validateCard(Long cardNumber){
        return transaction.validateCard(cardNumber);
    }

    @GetMapping(value = "/authenticateCard")
    public String authenticateCard(String authentication){
        if (CurrentCard.getINSTANCE().getCurrentCardSate() == CurrentCardSate.NONE)
            return "Please Insert Card";
        return transaction.authenticateCard(authentication);
    }
}
