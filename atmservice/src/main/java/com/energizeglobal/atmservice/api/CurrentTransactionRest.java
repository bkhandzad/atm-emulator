package com.energizeglobal.atmservice.api;

import com.energizeglobal.atmservice.action.DepositCash;
import com.energizeglobal.atmservice.action.Print;
import com.energizeglobal.atmservice.action.WithdrawCash;
import com.energizeglobal.atmservice.common.CurrentCardSate;
import com.energizeglobal.atmservice.dto.CurrentCard;
import com.energizeglobal.atmservice.service.CurrentTransactionService;
import com.energizeglobal.datamodel.CardTransactionDto;
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
    public String withDraw(@RequestBody BigDecimal amount) {
        try {
            return service.withDraw(amount);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Server not respond";
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackTransaction", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @PostMapping(value = "/deposit")
    public String deposit(@RequestBody BigDecimal amount) {
        try {
            return service.deposit(amount);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Server not respond";
        }
    }

    public void fallbackTransaction() {
        try {
            service.removeTransaction();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
