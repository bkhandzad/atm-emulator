package com.energizeglobal.bankservice.api;

import com.energizeglobal.bankservice.service.CardTransactionService;
import com.energizeglobal.datamodel.*;
import com.energizeglobal.datamodel.request.CardBalanceDto;
import com.energizeglobal.datamodel.request.TransactionsRequestDto;
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
    public ResponseEntity<CardTransactionDto> deposit(@RequestBody CardTransactionDto transaction) {
        return ResponseEntity.ok(service.deposit(transaction));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<CardTransactionDto> withdraw(@RequestBody CardTransactionDto transaction) {
        return ResponseEntity.ok(service.withdraw(transaction));
    }

    @DeleteMapping(value = "/remove")
    public void removeTransaction(@RequestBody Long id) {
        service.deleteById(id);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<CardTransactionDto>> getCardTransactions(@RequestBody TransactionsRequestDto transactionsRequestDto) {
        return ResponseEntity.ok(service.getLastTransactions(transactionsRequestDto.getCardNumber(), transactionsRequestDto.getCount()));
    }

    @GetMapping("/balance")
    public ResponseEntity<CardBalanceDto> getCardBalance(@RequestBody CustomerCardDto customerCard) {
        return ResponseEntity.ok(service.getCardBalance(customerCard.getCardNumber()));
    }
}
