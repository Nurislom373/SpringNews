package org.khasanof.springdatajpa.repository;

import org.khasanof.springdatajpa.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 5:55 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.repository
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByCityContaining(String city);

    boolean existsByCityIsContaining(String city);

    boolean existsByCityContains(String city);

    // The three methods to return the same results. - Containing, Contains and IsContaining:


    Optional<Address> findAllByCityEquals(String city);


}
