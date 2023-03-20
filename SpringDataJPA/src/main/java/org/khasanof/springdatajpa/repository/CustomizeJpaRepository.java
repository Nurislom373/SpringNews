package org.khasanof.springdatajpa.repository;

import org.khasanof.springdatajpa.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 3:42 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.repository
 */
@Repository
public interface CustomizeJpaRepository extends JpaRepository<Company, Long>, CustomizedSave<Company> {
}
