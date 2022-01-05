package com.energizeglobal.atmservice.dto;

import com.energizeglobal.datamodel.CustomerCardDto;

public class CurrentCard {

    private static CurrentCard INSTANCE;

    private CurrentCard(){
        currentCard  = new CustomerCardDto();
    }

    public static CurrentCard getINSTANCE(){
        if (INSTANCE == null){
            synchronized (CurrentCard.class){
                if(INSTANCE == null){
                    INSTANCE = new CurrentCard();
                }
            }
        }
        return INSTANCE;
    }

    CustomerCardDto currentCard;

    public void newCard(){
        currentCard=new CustomerCardDto();
    }

    public CustomerCardDto getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(CustomerCardDto currentCard) {
        this.currentCard = currentCard;
    }
}
