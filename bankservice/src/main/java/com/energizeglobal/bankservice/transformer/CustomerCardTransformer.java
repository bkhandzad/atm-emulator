package com.energizeglobal.bankservice.transformer;

import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import com.energizeglobal.datamodel.CustomerCardDto;
import com.energizeglobal.infrastructure.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(CustomerCardTransformer.BEAN_NAME)
public class CustomerCardTransformer extends AbstractTransformer<CustomerCardEntity,CustomerCardDto> {
    public static final String BEAN_NAME = "customerCardTransformer";

    @Autowired
    private CustomerTransformer customerTransformer;

    @Override
    public void transformValueObjectToEntity(CustomerCardDto input, CustomerCardEntity output) {
        output.setCustomerEntity(customerTransformer.createEntityFromValueObject(input.getCustomerDto()));
        output.setCardState(input.getCardState());
        output.setCardNumber(input.getCardNumber());
        output.setAccountNumber(input.getAccountNumber());
        output.setCardPIN(input.getCardPIN());
        output.setCvv2(input.getCvv2());
        output.setIssueDate(input.getIssueDate());
        output.setExpireDate(input.getExpireDate());
        output.setIncorrectPIN(input.getIncorrectPIN());
        setSystematicField(input, output);
    }

    @Override
    public void transformEntityToValueObject(CustomerCardEntity input, CustomerCardDto output) {
        output.setCustomerDto(customerTransformer.createValueObjectFromEntity(input.getCustomerEntity()));
        output.setCardState(input.getCardState());
        output.setCardNumber(input.getCardNumber());
        output.setAccountNumber(input.getAccountNumber());
        output.setCardPIN(input.getCardPIN());
        output.setCvv2(input.getCvv2());
        output.setIssueDate(input.getIssueDate());
        output.setExpireDate(input.getExpireDate());
        output.setIncorrectPIN(input.getIncorrectPIN());
        setSystematicField(input, output);

    }
}
