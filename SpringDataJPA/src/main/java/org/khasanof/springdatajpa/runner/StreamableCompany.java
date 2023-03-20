package org.khasanof.springdatajpa.runner;

import org.khasanof.springdatajpa.domain.Company;
import org.khasanof.springdatajpa.repository.StreamableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 3:24 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.runner
 */
//@Component
public class StreamableCompany implements CommandLineRunner {

//    @Autowired
    private StreamableRepository repository;

    @Override
    public void run(String... args) throws Exception {

        // Streamable example
        Streamable<Company> streamable = repository.findByEmail("av")
                .and(repository.findByEmployeeCount(100L));

        List<Company> list = streamable.toList();
        System.out.println("list = " + list);
    }
}
