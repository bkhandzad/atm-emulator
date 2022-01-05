package com.energizeglobal.bankservice.api;

import com.energizeglobal.bankservice.service.CustomerCardService;
import com.energizeglobal.datamodel.CustomerCardDto;
import com.energizeglobal.datamodel.request.CardDataRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card")
public class CustomerCardRest {

    @Autowired
    private CustomerCardService service;

    @PostMapping("/findCustomerCard")
    public ResponseEntity<CardDataRequestDto> findCustomerCard(@RequestBody CardDataRequestDto customerCard) {
        return ResponseEntity.ok(service.findCustomerCard(customerCard.getCardNumber()));
    }

    @PostMapping("/validate")
    public ResponseEntity<CardDataRequestDto> validateCard(@RequestBody CardDataRequestDto customerCard) {
        return ResponseEntity.ok(service.validateCard(customerCard.getCardNumber(), customerCard.getCardAuthenticationValue()));
    }
}
