package com.energizeglobal.atmservice.dto;

import com.energizeglobal.datamodel.CustomerCard;

public class CurrentCard {

    private static CurrentCard INSTANCE;

    private CurrentCard(){
        currentCard  = new CustomerCard();
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

    CustomerCard currentCard;

    public void newCard(){
        currentCard=new CustomerCard();
    }

    public CustomerCard getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(CustomerCard currentCard) {
        this.currentCard = currentCard;
    }
}
