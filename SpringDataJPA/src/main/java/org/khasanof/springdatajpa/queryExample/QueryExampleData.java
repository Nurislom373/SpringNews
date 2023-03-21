package org.khasanof.springdatajpa.queryExample;

import lombok.RequiredArgsConstructor;
import org.khasanof.springdatajpa.domain.Company;
import org.khasanof.springdatajpa.repository.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 4:00 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.queryExample
 */
//@Component
@RequiredArgsConstructor
public class QueryExampleData implements CommandLineRunner {

    private final CompanyRepository repository;

    @Override
    public void run(String... args) throws Exception {
        matcherMatchingFirst();
    }

    private void start() {
        Company company = new Company();
        company.setId(1L);

        Example<Company> companyExample = Example.of(company);
        Optional<Company> optional = repository.findOne(companyExample);
        Company company1 = optional.get();
        System.out.println("company1 = " + company1);
    }

    private void matcherMatchingFirst() {
        Company company = new Company();
        company.setName("Micro");
        company.setEmail("fghuij@gmail.com");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::startsWith)
                .withMatcher("email", ExampleMatcher.GenericPropertyMatcher::exact);

        // Second Example
        ExampleMatcher.GenericPropertyMatcher propertyMatcher = new ExampleMatcher.GenericPropertyMatcher();

        ExampleMatcher.matching()
                .withMatcher("name", propertyMatcher.exact()) // equal
                .withMatcher("email", propertyMatcher.startsWith()); // start with

        Example<Company> companyExample = Example.of(company, matcher);
        Optional<Company> optional = repository.findOne(companyExample);
        Company company1 = optional.get();
        System.out.println("company1 = " + company1);
    }

    private void findByFluentAPI() {
        Company company = new Company();
        company.setName("Micro");
        company.setEmail("fghuij@gmail.com");

        Optional<Company> optional = repository.findBy(Example.of(company),
                q -> q.sortBy(Sort.by("id").descending())
                        .first());

        Company company1 = optional.get();
        System.out.println("company1 = " + company1);
    }

    private ExampleMatcher createExampleMatcher() {
        return ExampleMatcher.matching()
                .withIgnorePaths("id", "employeeCount")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
    }
}
