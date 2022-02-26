package com.energizeglobal.atmservice.common;

import com.energizeglobal.atmservice.api.CardTransactionRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component("Bank")
public class BankHealthCheck implements HealthIndicator {

    private final Logger logger = LoggerFactory.getLogger(CardTransactionRest.class);

    private static final String URL = PropertiesCache.getInstance().getProperty("service.login");

    @Override
    public Health health() {
        try (Socket socket =
                     new Socket(new java.net.URL(URL).getHost(),8083)) {
        } catch (Exception e) {
            logger.warn("Failed to connect to: {}",URL);
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
        return Health.up().build();
    }
}
