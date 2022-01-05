package com.energizeglobal.bankservice.api;

import com.energizeglobal.bankservice.service.AtmMachineService;
import com.energizeglobal.datamodel.AtmMachineDto;
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
    public ResponseEntity<AtmMachineDto> login(@RequestBody AtmMachineDto atmMachine){
        return ResponseEntity.ok(service.login(atmMachine.getUserName(),atmMachine.getPassWord()));
    }


}
