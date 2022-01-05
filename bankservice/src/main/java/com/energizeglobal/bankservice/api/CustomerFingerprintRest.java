package com.energizeglobal.bankservice.api;

import com.energizeglobal.datamodel.CustomerFingerprintDto;
import com.energizeglobal.bankservice.service.CustomerFingerprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/fingerprint")
public class CustomerFingerprintRest {

    @Autowired
    private CustomerFingerprintService service;

    @GetMapping(value = "findFingerprint")
    public ResponseEntity<CustomerFingerprintDto> findFingerprint(@RequestBody Long customerId){
        return ResponseEntity.ok(service.findFingerprint(customerId));
    }
}
