package com.energizeglobal.bankservice.api;

import com.energizeglobal.bankservice.domain.AtmMachineEntity;
import com.energizeglobal.bankservice.dto.AtmMachineDto;
import com.energizeglobal.bankservice.service.AtmMachineService;
import com.energizeglobal.datamodel.AtmMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/atm")
public class AtmMachineRest {

    @Autowired
    private AtmMachineService service;

    @GetMapping("/findAll")
    public List<AtmMachineDto> findAll(){
        return service.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<AtmMachine> login(@RequestBody AtmMachine atmMachine){
        return ResponseEntity.ok(service.login(atmMachine.getUsername(),atmMachine.getPassword()));
    }


}
