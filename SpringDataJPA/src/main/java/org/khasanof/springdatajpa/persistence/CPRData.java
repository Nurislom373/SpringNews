package org.khasanof.springdatajpa.persistence;

import lombok.RequiredArgsConstructor;
import org.khasanof.springdatajpa.domain.Company;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 5:17 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.persistence
 */
@Component
@RequiredArgsConstructor
public class CPRData implements CommandLineRunner {

    private final CompanyPersistenceRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<Company> list = repository.findByIdOrderLimitedTo(3);
        System.out.println("list = " + list);
    }
}
