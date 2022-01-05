package com.energizeglobal.bankservice;

import com.energizeglobal.bankservice.domain.AtmMachineEntity;
import com.energizeglobal.bankservice.service.AtmMachineService;
import com.energizeglobal.datamodel.AtmMachineDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BankServiceApplicationTests {

	@Autowired
	AtmMachineService atmMachineService;

	@Test
	@Order(1)
	void saveAtmMachineEntity() {
		AtmMachineDto atmMachine = new AtmMachineDto(1L, "Admin", "admin", LocalDateTime.now(), LocalDateTime.now(), 1);
		AtmMachineEntity atmMachineEntity = atmMachineService.save(atmMachine);
		Assertions.assertEquals(atmMachineEntity.getId(), 1L);
	}

}
