package org.khasanof.springdatajpa.runner;

import org.khasanof.springdatajpa.domain.Company;
import org.khasanof.springdatajpa.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 4:25 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.runner
 */
@Component
public class CompanyData implements CommandLineRunner {

    @Autowired
    private CompanyRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Company pdp = new Company("Microsoft", "fghuij@gmail.com", 45678990L);
        repository.save(pdp);
    }
}
