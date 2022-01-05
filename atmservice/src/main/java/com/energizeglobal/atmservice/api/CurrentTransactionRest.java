package com.energizeglobal.atmservice.api;

import com.energizeglobal.atmservice.dto.TransactionAmount;
import com.energizeglobal.atmservice.service.CurrentTransactionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController(value = "/transaction")
public class CurrentTransactionRest {

    private final Logger logger = LoggerFactory.getLogger(CurrentTransactionRest.class);

    @Autowired
    @Qualifier(CurrentTransactionService.BEAN_NAME)
    CurrentTransactionService service;

    @HystrixCommand(fallbackMethod = "fallbackTransaction", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @PostMapping(value = "/withDraw")
    public String withDraw(@RequestBody TransactionAmount amount) throws InterruptedException  {
        try {
            return service.withDraw(amount.getAmount());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Server not respond");
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackTransaction", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @PostMapping(value = "/deposit")
    public String deposit(@RequestBody TransactionAmount amount) throws InterruptedException {
        try {
            return service.deposit(amount.getAmount());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Server not respond");
        }
    }

    public String fallbackTransaction(TransactionAmount amount) {
        try {
            service.removeTransaction();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "Transaction reverted";
    }

    @GetMapping("/balance")
    public String getCardBalance(){
        return service.getCardBalance();
    }
}
