package com.energizeglobal.atmservice.api;

import com.energizeglobal.atmservice.dto.TransactionAmount;
import com.energizeglobal.atmservice.service.CardTransactionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/transaction")
public class CardTransactionRest {

    private final Logger logger = LoggerFactory.getLogger(CardTransactionRest.class);

    @Autowired
    @Qualifier(CardTransactionService.BEAN_NAME)
    CardTransactionService service;

    @HystrixCommand(fallbackMethod = "fallbackTransaction", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @PostMapping(value = "/withDraw")
    public String withDraw(@RequestBody TransactionAmount amount) throws Exception  {
        try {
            return service.withDraw(amount.getAmount());
        } catch (RuntimeException re){
            logger.error(re.getMessage());
            throw new RuntimeException(re.getMessage());
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Server not respond");
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackTransaction", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @PostMapping(value = "/deposit")
    public String deposit(@RequestBody TransactionAmount amount) throws Exception {
        try {
            return service.deposit(amount.getAmount());
        } catch (RuntimeException re) {
            logger.error(re.getMessage());
            throw new RuntimeException(re.getMessage());
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
