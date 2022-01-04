package com.energizeglobal.bankservice.service;

import com.energizeglobal.bankservice.dto.CustomerFingerprintDto;
import com.energizeglobal.bankservice.exceptin.ServiceException;
import com.energizeglobal.bankservice.repository.CustomerFingerprintRepository;
import com.energizeglobal.bankservice.transformer.CustomerFingerprintTransformer;
import com.energizeglobal.bankservice.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CustomerFingerprintService.BEAN_NAME)
public class CustomerFingerprintService {
    public static final String BEAN_NAME = "customerFingerprintService";

    @Autowired
    private CustomerFingerprintRepository customerFingerprintRepository;

    @Autowired
    private CustomerFingerprintTransformer customerFingerprintTransformer;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerTransformer customerTransformer;

    public CustomerFingerprintDto findFingerprint(Long customerId){
        return customerFingerprintTransformer.createValueObjectFromEntity(customerFingerprintRepository.findByCustomerEntity(customerTransformer.createEntityFromValueObject(
                customerService.findCustomerById(customerId))).orElseThrow(() ->new ServiceException("Invalid Customer Fingerprint "+customerId+" Not found")));
    }
}
