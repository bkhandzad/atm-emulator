package com.energizeglobal.bankservice.service;

import com.energizeglobal.datamodel.CustomerDto;
import com.energizeglobal.infrastructure.exceptin.ServiceException;
import com.energizeglobal.bankservice.repository.CustomerRepository;
import com.energizeglobal.bankservice.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CustomerService.BEAN_NAME)
public class CustomerService {
    public static final String BEAN_NAME = "customerService";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTransformer customerTransformer;

    public CustomerDto findCustomerById(Long id){
        return customerTransformer.createValueObjectFromEntity(customerRepository.findById(id).orElseThrow(() ->new ServiceException("Invalid Customer ID "+id+" Not found")));
    }

    public CustomerDto findCustomerByNationalId(Long nationalId){
        return customerTransformer.createValueObjectFromEntity(customerRepository.findByNationalId(nationalId).orElseThrow(() ->new ServiceException("Invalid NationalId "+nationalId+" Not found")));
    }
}
