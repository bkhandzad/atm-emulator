package com.energizeglobal.atmservice.action;

import com.energizeglobal.datamodel.Transaction;

import java.util.List;

public class Print {
    public static synchronized boolean printTransaction(Transaction value){
        System.out.println(value.toString());
        return true;
    }

    public static synchronized boolean printTransactions(List<Transaction> values){
        System.out.println(values.toString());
        return true;
    }
}
