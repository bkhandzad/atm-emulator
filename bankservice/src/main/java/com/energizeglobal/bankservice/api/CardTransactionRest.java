package com.energizeglobal.bankservice.api;

import com.energizeglobal.bankservice.service.CardTransactionService;
import com.energizeglobal.datamodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transaction")
public class CardTransactionRest {

    @Autowired
    private CardTransactionService service;

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(service.deposit(transaction));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(service.withdraw(transaction));
    }

    @DeleteMapping(value = "/remove")
    public void removeTransaction(@RequestBody Long id) {
        service.deleteById(id);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getCardTransactions(@RequestBody TransactionsRequest transactionsRequest) {
        return ResponseEntity.ok(service.getLastTransactions(transactionsRequest.getCardNumber(), transactionsRequest.getCount()));
    }

    @GetMapping("/balance")
    public ResponseEntity<CardBalanceVo> getCardBalance(@RequestBody CustomerCard customerCard) {
        return ResponseEntity.ok(service.getCardBalance(customerCard.getCardNumber()));
    }
}
