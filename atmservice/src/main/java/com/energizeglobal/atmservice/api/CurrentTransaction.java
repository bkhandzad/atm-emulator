package com.energizeglobal.atmservice.api;

import com.energizeglobal.atmservice.action.Print;
import com.energizeglobal.atmservice.action.WithdrawCash;
import com.energizeglobal.atmservice.dto.CurrentCard;
import com.energizeglobal.atmservice.repository.CardTransaction;
import com.energizeglobal.datamodel.CardTransactionDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController(value = "/transaction")
public class CurrentTransaction {
    private static Long currentTransactionID = -1L;

    private Logger logger = LoggerFactory.getLogger(Logger.class);

    @Autowired
    CardTransaction cardTransaction;

    @HystrixCommand(fallbackMethod = "fallbackTransaction", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @PostMapping(value = "/withDraw")
    public String withDraw(@RequestBody BigDecimal amount){
        try {
            if (CurrentCard.getINSTANCE().getCurrentCard().getCardNumber().toString().length() < 10)
                return "Please Insert Card";
            currentTransactionID = -1L;
            CardTransactionDto transaction = cardTransaction.withdrawTransaction(amount);
            currentTransactionID = transaction.getId();
            WithdrawCash.withdrawCash(amount);
            Print.printTransaction(transaction);
            return "OK";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "Server not respond";
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackTransaction", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @PostMapping(value = "/deposit")
    public String deposit(@RequestBody BigDecimal amount){
        try {
        if (CurrentCard.getINSTANCE().getCurrentCard().getCardNumber().toString().length() < 10)
            return "Please Insert Card";
        currentTransactionID = -1L;
        CardTransactionDto transaction = cardTransaction.depositTransaction(amount);
        currentTransactionID = transaction.getId();
        WithdrawCash.withdrawCash(amount);
        Print.printTransaction(transaction);
        return "OK";
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return "Server not respond";
        }
    }

    public void fallbackTransaction(){
        if (currentTransactionID > 0) {
            cardTransaction.removeTransaction(currentTransactionID);
        }
    }
}
