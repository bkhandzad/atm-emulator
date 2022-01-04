package com.energizeglobal.bankservice.api;

import com.energizeglobal.bankservice.dto.CustomerDto;
import com.energizeglobal.bankservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerRest {

    @Autowired
    private CustomerService service;

    @GetMapping("/findById")
    public ResponseEntity<CustomerDto> findCustomerById(@RequestBody Long id){
        return ResponseEntity.ok(service.findCustomerById(id));
    }

    @GetMapping("/findByNationalId")
    public ResponseEntity<CustomerDto> findCustomerByNationalId(@RequestBody Long nationalId){
        return ResponseEntity.ok(service.findCustomerByNationalId(nationalId));
    }
}
