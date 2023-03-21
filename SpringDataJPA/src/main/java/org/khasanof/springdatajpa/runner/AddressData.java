package org.khasanof.springdatajpa.runner;

import lombok.RequiredArgsConstructor;
import org.khasanof.springdatajpa.domain.Address;
import org.khasanof.springdatajpa.repository.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 6:03 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.runner
 */
@Component
@RequiredArgsConstructor
public class AddressData implements CommandLineRunner {

    private final AddressRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address("Tashkent", "Beruniy B16", "67890");
        repository.save(address);

    }
}
