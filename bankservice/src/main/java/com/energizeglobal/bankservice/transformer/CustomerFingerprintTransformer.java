package com.energizeglobal.bankservice.transformer;

import com.energizeglobal.bankservice.domain.CustomerFingerprintEntity;
import com.energizeglobal.bankservice.dto.CustomerFingerprintDto;
import com.energizeglobal.bankservice.infrastructure.AbstractTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(CustomerFingerprintTransformer.BEAN_NAME)
public class CustomerFingerprintTransformer  extends AbstractTransformer<CustomerFingerprintEntity,CustomerFingerprintDto> {
    public static final String BEAN_NAME = "customerFingerprintTransformer";

    @Autowired
    private CustomerTransformer customerTransformer;

    @Override
    public void transformValueObjectToEntity(CustomerFingerprintDto input, CustomerFingerprintEntity output) {
        output.setCustomerEntity(customerTransformer.createEntityFromValueObject(input.getCustomerDto()));
        output.setFingerprint(input.getFingerprint());
        setSystematicField(input, output);
    }

    @Override
    public void transformEntityToValueObject(CustomerFingerprintEntity input, CustomerFingerprintDto output) {
        output.setCustomerDto(customerTransformer.createValueObjectFromEntity(input.getCustomerEntity()));
        output.setFingerprint(input.getFingerprint());
        setSystematicField(input, output);
    }
}
