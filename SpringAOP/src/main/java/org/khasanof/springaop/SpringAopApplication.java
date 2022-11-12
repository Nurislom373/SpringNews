package org.khasanof.springaop;

import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

}

@Service
class DrugService {

	private Drug drug;

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}
}

@Aspect
class DrugAspect {

	@Before("execution(public String getName())")
	public void getAdvice() {
		System.out.println("Executing Advice on getName()");
	}

	@Before("execution(* org.khasanof.springaop.*.get*())")
	public void getAllAdvice() {
		System.out.println("Service method getter called!");
	}

	@Before("getNamePointcut()")
	public void loggingAdvice(){
		System.out.println("Executing loggingAdvice on getName()");
	}

	@Before("getNamePointcut()")
	public void secondAdvice(){
		System.out.println("Executing secondAdvice on getName()");
	}

	@Pointcut("execution(public String getName())")
	public void getNamePointcut(){}

	@Before("allMethodsPointcut()")
	public void allServiceMethodsAdvice(){
		System.out.println("Before executing service method");
	}

	//Pointcut to execute on all the methods of classes in a package
	@Pointcut("within(org.khasanof.springaop.*)")
	public void allMethodsPointcut(){}
}

@Component
class Data implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		DrugService drugService = applicationContext.getBean("drugService", DrugService.class);
		drugService.setDrug(new Drug("Mezzaz"));
		System.out.println(drugService.getDrug().getName());
		drugService.getDrug().setName("Mezza");
	}
}



@AllArgsConstructor
class Drug {

	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void thrException() {
		throw new RuntimeException("Dummy Exception");
	}
}
