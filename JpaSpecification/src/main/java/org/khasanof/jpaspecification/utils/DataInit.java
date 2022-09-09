package org.khasanof.jpaspecification.utils;

import lombok.SneakyThrows;
import org.khasanof.jpaspecification.entity.Company;
import org.khasanof.jpaspecification.entity.Employee;
import org.khasanof.jpaspecification.repository.CompanyRepository;
import org.khasanof.jpaspecification.repository.EmployeeRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInit implements ApplicationContextAware {

    @SneakyThrows
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        EmployeeRepository employeeRepository = applicationContext.getBean(EmployeeRepository.class);
//        CompanyRepository companyRepository = applicationContext.getBean(CompanyRepository.class);
//
//        List<Company> companies = CSVParser.parseObjectList("src/main/resources/data/COM_MOCK_DATA.csv", Company.class);
//        companyRepository.saveAll(companies);
//
//        List<Employee> employees = CSVParser.parseObjectList("src/main/resources/data/EMP_MOCK_DATA.csv", Employee.class);
//
//        for (Employee employee : employees) {
//            employee.setCompanyId(randomBetween(1, 50));
//            employeeRepository.save(employee);
//        }
        // only need to run once!
    }

    public int randomBetween(int a, int b) {
        return a + (int) (Math.random() * b);
    }

}
