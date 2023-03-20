package org.khasanof.springdatajpa.runner;

import org.khasanof.springdatajpa.domain.Company;
import org.khasanof.springdatajpa.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 2:51 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.runner
 */
//@Component
public class SortCompany implements CommandLineRunner {

//    @Autowired
    private CompanyRepository repository;

    @Override
    public void run(String... args) throws Exception {

        // Sort First Example
        Sort sort1 = Sort.by("count").descending()
                .and(Sort.by("email")).ascending();

        List<Company> list1 = repository.findAllByEmployeeCountGreaterThan(100L, sort1);
        System.out.println("list1 = " + list1);

        // Sort Second Example
        Sort.TypedSort<Company> company = Sort.sort(Company.class);

        Sort sort2 = company.by(Company::getId).ascending()
                .and(company.by(Company::getCreatedAt)).descending();

        List<Company> list2 = repository.findAllByEmployeeCountGreaterThan(100L, sort2);
        System.out.println("list2 = " + list2);
    }
}
