package com.energizeglobal.atmservice.dto;

import com.energizeglobal.atmservice.common.CurrentCardSate;
import com.energizeglobal.datamodel.CustomerCardDto;
import com.energizeglobal.datamodel.request.CardDataRequestDto;

public class CurrentCard {

    private static CurrentCard INSTANCE;

    private CustomerCardDto currentCard;

    private CardDataRequestDto cardDataRequestDto;

    private CurrentCardSate currentCardSate;

    private CurrentCard() {
        newCard();
    }

    public static CurrentCard getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (CurrentCard.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CurrentCard();
                }
            }
        }
        return INSTANCE;
    }

    public CurrentCardSate getCurrentCardSate() {
        return currentCardSate;
    }

    public void setCurrentCardSate(CurrentCardSate currentCardSate) {
        this.currentCardSate = currentCardSate;
    }

    public void newCard() {
        currentCard = new CustomerCardDto();
        cardDataRequestDto = new CardDataRequestDto();
        currentCardSate = CurrentCardSate.NONE;
    }

    public CustomerCardDto getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(CustomerCardDto currentCard) {
        this.currentCard = currentCard;
    }

    public CardDataRequestDto getCardDataRequestDto() {
        return cardDataRequestDto;
    }

    public void setCardDataRequestDto(CardDataRequestDto cardDataRequestDto) {
        this.cardDataRequestDto = cardDataRequestDto;
    }

    public void setId(Long id) {
        this.currentCard.setId(id);
        this.cardDataRequestDto.setId(id);
    }

    public void setCardNumber(Long cardNumber) {
        this.currentCard.setCardNumber(cardNumber);
        this.cardDataRequestDto.setCardNumber(cardNumber);
    }

    public void setCardAuthenticationValue(String cardAuthenticationValue) {
        this.cardDataRequestDto.setCardAuthenticationValue(cardAuthenticationValue);
    }
}
