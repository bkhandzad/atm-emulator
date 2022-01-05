package com.energizeglobal.atmservice.service;

import com.energizeglobal.atmservice.action.DepositCash;
import com.energizeglobal.atmservice.action.Print;
import com.energizeglobal.atmservice.action.WithdrawCash;
import com.energizeglobal.datamodel.types.CurrentCardSate;
import com.energizeglobal.atmservice.dto.CurrentCard;
import com.energizeglobal.atmservice.repository.CardTransaction;
import com.energizeglobal.datamodel.CardTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service(CurrentTransactionService.BEAN_NAME)
public class CurrentTransactionService {
    public static final String BEAN_NAME = "currentTransactionService";

    @Autowired
    CardTransaction cardTransaction;

    public String withDraw( BigDecimal amount) {
        if (CurrentCard.getINSTANCE().getCurrentCardSate() == CurrentCardSate.NONE)
            return "Please Insert Card";
        if (CurrentCard.getINSTANCE().getCurrentCardSate() == CurrentCardSate.CARD_INSERTED)
            return "Please enter card authentication";
        CurrentCard.getINSTANCE().setCurrentTransactionID(-1L);
        CardTransactionDto transaction = cardTransaction.withdrawTransaction(amount);
        CurrentCard.getINSTANCE().setCurrentTransactionID(transaction.getId());
        WithdrawCash.withdrawCash(amount);
        Print.printTransaction(transaction);
        return "OK";
    }


    public String deposit( BigDecimal amount) {
        if (CurrentCard.getINSTANCE().getCurrentCardSate() == CurrentCardSate.NONE)
            return "Please Insert Card";
        if (CurrentCard.getINSTANCE().getCurrentCardSate() == CurrentCardSate.CARD_INSERTED)
            return "Please enter card authentication";
        CurrentCard.getINSTANCE().setCurrentTransactionID(-1L);
        CardTransactionDto transaction = cardTransaction.depositTransaction(amount);
        CurrentCard.getINSTANCE().setCurrentTransactionID(transaction.getId());
        DepositCash.depositCash(amount);
        Print.printTransaction(transaction);
        return "OK";
    }

    public void removeTransaction() {
        if (CurrentCard.getINSTANCE().getCurrentTransactionID().compareTo(-1L) > 0)
            cardTransaction.removeTransaction(CurrentCard.getINSTANCE().getCurrentTransactionID());
    }
}
