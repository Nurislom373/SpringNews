package org.khasanof.springaop;

import org.junit.jupiter.api.Test;
import org.khasanof.springaop.config.aspc.EmployeeManager;
import org.khasanof.springaop.config.aspc.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAopApplicationTests {

	@Autowired
	TestService service;

	@Autowired
	EmployeeManager manager;

	@Test
	void contextLoads() {
		service.stuff();
		service.observer();
		manager.getEmployeeById(1);
	}

}
