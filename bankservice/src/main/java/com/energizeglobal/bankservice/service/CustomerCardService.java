package com.energizeglobal.bankservice.service;

import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import com.energizeglobal.datamodel.CustomerCardDto;
import com.energizeglobal.datamodel.types.AuthMethod;
import com.energizeglobal.datamodel.types.CardState;
import com.energizeglobal.infrastructure.exceptin.ServiceException;
import com.energizeglobal.bankservice.repository.CustomerCardRepository;
import com.energizeglobal.bankservice.transformer.CustomerCardTransformer;
import com.energizeglobal.datamodel.types.CardAuthResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(CustomerCardService.BEAN_NAME)
public class CustomerCardService {
    public static final String BEAN_NAME = "customerCardService";

    @Autowired
    private CustomerCardRepository customerCardRepository;

    @Autowired
    private CustomerCardTransformer customerCardTransformer;

    @Autowired
    private CustomerFingerprintService customerFingerprintService;

    public CustomerCardDto findCustomerCard(Long cardNumber){
        CustomerCardDto customerCard = new CustomerCardDto();
        Optional<CustomerCardEntity> entity = customerCardRepository.findByCardNumber(cardNumber);
        if (entity.isPresent()) {
            customerCard.setId(entity.get().getId());
            customerCard.setCardNumber(entity.get().getCardNumber());
            if (customerCard.getCardState() == CardState.USABLE)
                customerCard.setError(CardAuthResult.OK);
            else if (customerCard.getCardState() == CardState.BLOCK)
                customerCard.setError(CardAuthResult.BLOKE_CARD);
            else if (customerCard.getCardState() == CardState.READY)
                customerCard.setError(CardAuthResult.CARD_NEED_PIN);
        }
        else {
            customerCard.setError(CardAuthResult.CARD_NOT_FOUND);
        }
        return customerCard;
    }

    public CustomerCardEntity findCustomerCardEntity(Long cardNumber){
        return customerCardRepository.findByCardNumber(cardNumber).orElseThrow(() ->new ServiceException("Invalid Card Number "+cardNumber+" Not found"));
    }

    public CustomerCardDto validateCard(Long cardNumber,String auth) {
        Optional<CustomerCardEntity> entity = customerCardRepository.findByCardNumber(cardNumber);
        CustomerCardDto returnValue = new CustomerCardDto();
        if (entity.isPresent()) {
            if (entity.get().getCardState().equals(CardState.READY)) {
                if (entity.get().getAuthMethod().equals(AuthMethod.PIN) && entity.get().getCardPIN().equals(Long.parseLong(auth))) {
                    returnValue.setId(entity.get().getId());
                    returnValue.setCardNumber(entity.get().getCardNumber());
                    returnValue.setAuthMethod(entity.get().getAuthMethod());
                    returnValue.setError(CardAuthResult.OK);
                } else if (entity.get().getAuthMethod() == AuthMethod.FINGERPRINT && customerFingerprintService.findFingerprint(entity.get().getCustomerEntity().getId()).getFingerprint().equals(auth)) {
                    returnValue.setId(entity.get().getId());
                    returnValue.setCardNumber(entity.get().getCardNumber());
                    returnValue.setAuthMethod(entity.get().getAuthMethod());
                    returnValue.setError(CardAuthResult.OK);
                } else {
                    returnValue.setError(CardAuthResult.INVALID_AUTH);
                    entity.get().setIncorrectPIN(entity.get().getIncorrectPIN() + 1);
                    if (entity.get().getIncorrectPIN().equals(3))
                        entity.get().setCardState(CardState.BLOCK);
                    customerCardRepository.save(entity.get());
                }
            }
            else
                returnValue.setError(CardAuthResult.BLOKE_CARD);
        }
        else
            returnValue.setError(CardAuthResult.CARD_NOT_FOUND);
        return returnValue;
    }
}
