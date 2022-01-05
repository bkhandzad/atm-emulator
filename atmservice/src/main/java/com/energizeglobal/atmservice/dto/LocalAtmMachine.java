package com.energizeglobal.atmservice.dto;

import com.energizeglobal.datamodel.AtmMachineDto;

public class LocalAtmMachine {

    private static LocalAtmMachine INSTANCE;

    private LocalAtmMachine() {
        localAtm = new AtmMachineDto();
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

    private AtmMachineDto localAtm;

    public AtmMachineDto getLocalAtm() {
        return localAtm;
    }
}
