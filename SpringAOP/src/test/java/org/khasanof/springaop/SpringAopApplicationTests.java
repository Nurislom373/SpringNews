package org.khasanof.springaop;

import org.junit.jupiter.api.Test;
import org.khasanof.springaop.advice.AdviceService;
import org.khasanof.springaop.pointcut.EmployeeManager;
import org.khasanof.springaop.pointcut.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAopApplicationTests {

	@Autowired
	TestService service;

	@Autowired
	EmployeeManager manager;

	@Autowired
	AdviceService adviceService;

	@Test
	void contextLoads() {
		service.stuff();
		service.observer();
		manager.getEmployeeById(1);
		manager.hiSay();
	}

	@Test
	void adviceCommonTest() {
		adviceService.testMethod();
	}

	@Test
	void exceptionMethodTest() {
		Object o = service.exceptionMethod();
		System.out.println("o = " + o);
	}

}
