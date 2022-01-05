package com.energizeglobal.bankservice.transformer;

import com.energizeglobal.bankservice.domain.CardTransactionEntity;
import com.energizeglobal.bankservice.dto.CardTransactionDto;
import com.energizeglobal.infrastructure.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(CardTransactionTransformer.BEAN_NAME)
public class CardTransactionTransformer extends AbstractTransformer<CardTransactionEntity,CardTransactionDto> {
    public static final String BEAN_NAME = "cardTransactionTransformer";

    @Autowired
    private AtmMachineTransformer atmMachineTransformer;

    @Autowired
    private CustomerCardTransformer customerCardTransformer;

    @Override
    public void transformValueObjectToEntity(CardTransactionDto input, CardTransactionEntity output) {
        output.setAtmMachineEntity(atmMachineTransformer.createEntityFromValueObject(input.getAtmMachineDto()));
        output.setCustomerCardEntity(customerCardTransformer.createEntityFromValueObject(input.getCustomerCardDto()));
        output.setTransactionType(input.getTransactionType());
        output.setTransactionAmount(input.getTransactionAmount());
        setSystematicField(input, output);
    }

    @Override
    public void transformEntityToValueObject(CardTransactionEntity input, CardTransactionDto output) {
        output.setAtmMachineDto(atmMachineTransformer.createValueObjectFromEntity(input.getAtmMachineEntity()));
        output.setCustomerCardDto(customerCardTransformer.createValueObjectFromEntity(input.getCustomerCardEntity()));
        output.setTransactionType(input.getTransactionType());
        output.setTransactionAmount(input.getTransactionAmount());
        setSystematicField(input, output);
    }
}
