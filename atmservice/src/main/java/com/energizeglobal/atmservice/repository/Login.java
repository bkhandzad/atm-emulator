package com.energizeglobal.atmservice.repository;

import com.energizeglobal.atmservice.common.PropertiesCache;
import com.energizeglobal.atmservice.dto.LocalAtmMachine;
import com.energizeglobal.datamodel.AtmMachineDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class Login {
    private static Logger logger = LoggerFactory.getLogger(Logger.class);

    public static void login(){
        try {
            LocalAtmMachine.getINSTANCE().getLocalAtm().setUserName(PropertiesCache.getInstance().getProperty("atm.username"));
            LocalAtmMachine.getINSTANCE().getLocalAtm().setPassWord(PropertiesCache.getInstance().getProperty("atm.password"));

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AtmMachineDto> atmMachineHttpEntity = new HttpEntity<>(LocalAtmMachine.getINSTANCE().getLocalAtm(), headers);
            ResponseEntity<AtmMachineDto> responseEntity = restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.login"), atmMachineHttpEntity, AtmMachineDto.class);
            LocalAtmMachine.getINSTANCE().getLocalAtm().setId(responseEntity.getBody().getId());
            logger.info("Login successful");
            logger.info("Atm ID = " + LocalAtmMachine.getINSTANCE().getLocalAtm().getId());
        }
        catch (Exception e) {
            logger.error("Could not login");
            logger.error(e.getMessage());
            System.exit(0) ;
        }
    }
}
