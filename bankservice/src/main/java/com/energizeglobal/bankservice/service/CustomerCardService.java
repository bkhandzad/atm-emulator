package com.energizeglobal.bankservice.service;

import com.energizeglobal.bankservice.domain.CustomerCardEntity;
import com.energizeglobal.datamodel.request.CardDataRequestDto;
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

    public CardDataRequestDto findCustomerCard(Long cardNumber){
        CardDataRequestDto customerCard = new CardDataRequestDto();
        Optional<CustomerCardEntity> entity = customerCardRepository.findByCardNumber(cardNumber);
        if (entity.isPresent()) {
            customerCard.setId(entity.get().getId());
            customerCard.setCardNumber(entity.get().getCardNumber());
            customerCard.setAuthMethod(entity.get().getAuthMethod());
            customerCard.setCardState(entity.get().getCardState());
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

    public CardDataRequestDto validateCard(Long cardNumber, String auth) {
        Optional<CustomerCardEntity> entity = customerCardRepository.findByCardNumber(cardNumber);
        CardDataRequestDto returnValue = new CardDataRequestDto();
        if (entity.isPresent()) {
            if (entity.get().getCardState().equals(CardState.USABLE)) {
                if (entity.get().getAuthMethod().equals(AuthMethod.PIN) && entity.get().getCardPIN().equals(Long.parseLong(auth))) {
                    returnValue.setId(entity.get().getId());
                    returnValue.setCardNumber(entity.get().getCardNumber());
                    returnValue.setAuthMethod(entity.get().getAuthMethod());
                    returnValue.setCardState(entity.get().getCardState());
                    returnValue.setError(CardAuthResult.OK);
                    if (entity.get().getIncorrectPIN() > 0){
                        entity.get().setCardPIN(0L);
                        customerCardRepository.save(entity.get());
                    }
                } else if (entity.get().getAuthMethod() == AuthMethod.FINGERPRINT && customerFingerprintService.findFingerprint(entity.get().getCustomerEntity().getId()).getFingerprint().equals(auth)) {
                    returnValue.setId(entity.get().getId());
                    returnValue.setCardNumber(entity.get().getCardNumber());
                    returnValue.setAuthMethod(entity.get().getAuthMethod());
                    returnValue.setCardState(entity.get().getCardState());
                    returnValue.setError(CardAuthResult.OK);
                    if (entity.get().getIncorrectPIN() > 0){
                        entity.get().setCardPIN(0L);
                        customerCardRepository.save(entity.get());
                    }
                } else {
                    returnValue.setError(CardAuthResult.INVALID_AUTH);
                    entity.get().setIncorrectPIN(entity.get().getIncorrectPIN() + 1);
                    if (entity.get().getIncorrectPIN().equals(3))
                        entity.get().setCardState(CardState.BLOCK);
                    customerCardRepository.save(entity.get());
                }
            }
            else if (entity.get().getCardState().equals(CardState.BLOCK))
                returnValue.setError(CardAuthResult.BLOKE_CARD);
            else if (entity.get().getCardState().equals(CardState.READY))
                returnValue.setError(CardAuthResult.CARD_NEED_PIN);
        }
        else
            returnValue.setError(CardAuthResult.CARD_NOT_FOUND);
        return returnValue;
    }
}
