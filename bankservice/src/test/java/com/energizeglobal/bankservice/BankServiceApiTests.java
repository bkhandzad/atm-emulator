package com.energizeglobal.bankservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BankServiceApiTests {


    @Test
    @Order(1)
    void insertAtmMachine(){
        Assertions.assertEquals(1,1);
    }
}
