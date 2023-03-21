package org.khasanof.springdatajpa.queryExample;

import org.khasanof.springdatajpa.domain.Company;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 4:01 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.queryExample
 */
@Repository
public interface QueryExampleRepository extends QueryByExampleExecutor<Company> {
}
