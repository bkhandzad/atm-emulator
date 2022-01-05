package com.energizeglobal.bankservice.transformer;

import com.energizeglobal.bankservice.domain.CustomerEntity;
import com.energizeglobal.datamodel.CustomerDto;
import com.energizeglobal.infrastructure.AbstractTransformer;
import org.springframework.stereotype.Component;

@Component(CustomerTransformer.BEAN_NAME)
public class CustomerTransformer extends AbstractTransformer<CustomerEntity, CustomerDto> {
    public static final String BEAN_NAME = "customerTransformer";

    @Override
    public void transformValueObjectToEntity(CustomerDto input, CustomerEntity output) {
        output.setNationalId(input.getNationalId());
        output.setFirstName(input.getFirstName());
        output.setLastName(input.getLastName());
        output.setCustomerType(input.getCustomerType());
        setSystematicField(input, output);
    }

    @Override
    public void transformEntityToValueObject(CustomerEntity input, CustomerDto output) {
        output.setNationalId(input.getNationalId());
        output.setFirstName(input.getFirstName());
        output.setLastName(input.getLastName());
        output.setCustomerType(input.getCustomerType());
        setSystematicField(input, output);
    }
}
