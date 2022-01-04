package com.energizeglobal.bankservice.service;

import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import com.energizeglobal.bankservice.domain.types.AuthMethod;
import com.energizeglobal.bankservice.domain.types.CardState;
import com.energizeglobal.bankservice.dto.CustomerCardDto;
import com.energizeglobal.bankservice.dto.CustomerFingerprintDto;
import com.energizeglobal.bankservice.exceptin.ServiceException;
import com.energizeglobal.bankservice.repository.CustomerCardRepository;
import com.energizeglobal.bankservice.transformer.CustomerCardTransformer;
import com.energizeglobal.datamodel.CustomerCard;
import com.energizeglobal.datamodel.type.CardAuthResult;
import net.bytebuddy.implementation.bytecode.Throw;
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

    public CustomerCard findCustomerCard(Long cardNumber){
        CustomerCard customerCard = new CustomerCard();
        Optional<CustomerCardEntity> entity = customerCardRepository.findByCardNumber(cardNumber);
        if (entity.isPresent()) {
            customerCard.setId(entity.get().getId());
            customerCard.setCardNumber(entity.get().getCardNumber());
            customerCard.setError(CardAuthResult.OK);
        }
        else {
            customerCard.setError(CardAuthResult.CARD_NOT_FOUND);
        }
        return customerCard;
    }

    public CustomerCardEntity findCustomerCardEntity(Long cardNumber){
        return customerCardRepository.findByCardNumber(cardNumber).orElseThrow(() ->new ServiceException("Invalid Card Number "+cardNumber+" Not found"));
    }

    public CustomerCard validateCard(Long cardNumber,String auth) {
        Optional<CustomerCardEntity> entity = customerCardRepository.findByCardNumber(cardNumber);
        CustomerCard returnValue = new CustomerCard();
        if (entity.isPresent()) {
            if (entity.get().getCardState().equals(CardState.READY)) {
                if (entity.get().getAuthMethod().equals(AuthMethod.PIN) && entity.get().getCardPIN().equals(Long.parseLong(auth))) {
                    returnValue.setId(entity.get().getId());
                    returnValue.setCardNumber(entity.get().getCardNumber());
                    returnValue.setCardAuthentication(entity.get().getAuthMethod().getValue());
                    returnValue.setError(CardAuthResult.OK);
                } else if (entity.get().getAuthMethod() == AuthMethod.FINGERPRINT && customerFingerprintService.findFingerprint(entity.get().getCustomerEntity().getId()).getFingerprint().equals(auth)) {
                    returnValue.setId(entity.get().getId());
                    returnValue.setCardNumber(entity.get().getCardNumber());
                    returnValue.setCardAuthentication(entity.get().getAuthMethod().getValue());
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
