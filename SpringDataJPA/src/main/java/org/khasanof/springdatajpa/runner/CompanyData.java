package org.khasanof.springdatajpa.runner;

import org.khasanof.springdatajpa.criteria.CompanyCriteria;
import org.khasanof.springdatajpa.criteria.CompanyPredicate;
import org.khasanof.springdatajpa.domain.Company;
import org.khasanof.springdatajpa.repository.CompanyRepository;
import org.khasanof.springdatajpa.specifications.CompanySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 4:25 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.runner
 */
//@Component
public class CompanyData implements CommandLineRunner {

//    @Autowired
    private CompanyRepository repository;

    @Override
    public void run(String... args) throws Exception {
        CompanySpecification specification = new CompanySpecification(new CompanyCriteria("id", ":", "1"));
        List<Company> all = repository.findAll(specification);
        System.out.println("all = " + all);
    }
}
