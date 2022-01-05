package com.energizeglobal.atmservice.api;

import com.energizeglobal.atmservice.service.CustomerCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/card")
public class CustomerCardRest {
    private final Logger logger = LoggerFactory.getLogger(CustomerCardRest.class);

    @Autowired
    private CustomerCardService service;

    @GetMapping(value = "/validateCard")
    public String validateCard(Long cardNumber){
        return service.validateCard(cardNumber);
    }

    @GetMapping(value = "/authenticateCard")
    public String authenticateCard(String authentication){
        return service.authenticateCard(authentication);
    }
}
