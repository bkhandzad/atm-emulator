package com.energizeglobal.atmservice.dto;

import com.energizeglobal.datamodel.AtmMachine;

public class LocalAtmMachine {

    private static LocalAtmMachine INSTANCE;

    private LocalAtmMachine() {
        localAtm = new AtmMachine();
    }

    public static LocalAtmMachine getINSTANCE(){
        if (INSTANCE == null){
            synchronized (LocalAtmMachine.class){
                if(INSTANCE == null){
                    INSTANCE = new LocalAtmMachine();
                }
            }
        }
        return INSTANCE;
    }

    private AtmMachine localAtm;

    public AtmMachine getLocalAtm() {
        return localAtm;
    }
}
