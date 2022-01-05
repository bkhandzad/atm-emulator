package com.energizeglobal.atmservice.action;

import com.energizeglobal.datamodel.CardTransactionDto;

import java.util.List;

public class Print {
    public static synchronized boolean printTransaction(CardTransactionDto value){
        System.out.println(value.toString());
        return true;
    }

    public static synchronized boolean printTransactions(List<CardTransactionDto> values){
        System.out.println(values.toString());
        return true;
    }
}
