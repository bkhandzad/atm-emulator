package com.energizeglobal.atmservice.repository;

import com.energizeglobal.atmservice.common.PropertiesCache;
import com.energizeglobal.atmservice.dto.LocalAtmMachine;
import com.energizeglobal.datamodel.AtmMachine;
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
            LocalAtmMachine.getINSTANCE().getLocalAtm().setUsername(PropertiesCache.getInstance().getProperty("atm.username"));
            LocalAtmMachine.getINSTANCE().getLocalAtm().setPassword(PropertiesCache.getInstance().getProperty("atm.password"));

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AtmMachine> atmMachineHttpEntity = new HttpEntity<>(LocalAtmMachine.getINSTANCE().getLocalAtm(), headers);
            ResponseEntity<AtmMachine> responseEntity = restTemplate.postForEntity(PropertiesCache.getInstance().getProperty("service.login"), atmMachineHttpEntity, AtmMachine.class);
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
