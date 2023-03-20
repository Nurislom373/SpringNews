package org.khasanof.springdatajpa.repository;

import org.khasanof.springdatajpa.domain.Company;
import org.khasanof.springdatajpa.runner.Companies;
import org.springframework.data.repository.Repository;
import org.springframework.data.util.Streamable;

import java.time.LocalDateTime;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 3:20 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.repository
 */
@org.springframework.stereotype.Repository
public interface StreamableRepository extends Repository<Company, Long> {

    Streamable<Company> findByEmail(String email);

    Streamable<Company> findByEmployeeCount(Long employeeCount);

    Companies findAllByCreatedAtIsBetween(LocalDateTime createdAt, LocalDateTime createdAt2);

}
