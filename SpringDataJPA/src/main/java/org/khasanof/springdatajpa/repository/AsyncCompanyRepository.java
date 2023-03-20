package org.khasanof.springdatajpa.repository;

import org.khasanof.springdatajpa.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 3:35 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.repository
 */
@Repository
public interface AsyncCompanyRepository extends JpaRepository<Company, Long> {

    @Async
    Future<Company> findCompanyByIdIs(@NonNull Long id);

    @Async
    CompletableFuture<Company> findCompanyByEmail(@NonNull String email);

}
