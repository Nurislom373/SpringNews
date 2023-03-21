package org.khasanof.springdatajpa.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.khasanof.springdatajpa.domain.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 4:43 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.persistence
 */
@Repository
public class CompanyPersistenceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Company> findByIdOrderLimitedTo(int limit) {
        return entityManager.createQuery("SELECT c FROM Company c ORDER BY c.id DESC", Company.class)
                .setMaxResults(limit).getResultList();
    }


}
