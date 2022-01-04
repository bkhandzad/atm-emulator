package com.energizeglobal.bankservice.api;

import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import com.energizeglobal.bankservice.dto.CustomerCardDto;
import com.energizeglobal.bankservice.service.CustomerCardService;
import com.energizeglobal.datamodel.CustomerCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card")
public class CustomerCardRest {

    @Autowired
    private CustomerCardService service;

    @GetMapping("/findCustomerCard")
    public ResponseEntity<CustomerCard> findCustomerCard(@RequestBody Long cardNumber){
        return ResponseEntity.ok(service.findCustomerCard(cardNumber));
    }

    @GetMapping("/validate")
    public ResponseEntity<CustomerCard> validateCard(@RequestBody CustomerCard customerCard){
        return ResponseEntity.ok(service.validateCard(customerCard.getCardNumber(),customerCard.getCardAuthentication()));
    }
}
