package com.energizeglobal.bankservice.api;

import com.energizeglobal.bankservice.service.CustomerCardService;
import com.energizeglobal.datamodel.CustomerCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card")
public class CustomerCardRest {

    @Autowired
    private CustomerCardService service;

    @GetMapping("/findCustomerCard")
    public ResponseEntity<CustomerCardDto> findCustomerCard(@RequestBody Long cardNumber){
        return ResponseEntity.ok(service.findCustomerCard(cardNumber));
    }

    @GetMapping("/validate")
    public ResponseEntity<CustomerCardDto> validateCard(@RequestBody CustomerCardDto customerCard){
        return ResponseEntity.ok(service.validateCard(customerCard.getCardNumber(),customerCard.getCardAuthenticationValue()));
    }
}
