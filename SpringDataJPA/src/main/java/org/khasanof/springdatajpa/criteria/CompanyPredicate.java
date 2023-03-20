package org.khasanof.springdatajpa.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
import org.khasanof.springdatajpa.domain.Company;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/20/2023
 * <br/>
 * Time: 5:55 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.criteria
 */
@AllArgsConstructor
public class CompanyPredicate {

    private CompanyCriteria criteria;

    public BooleanExpression getPredicate() {
        PathBuilder<Company> entityPath = new PathBuilder<>(Company.class, "company");

        StringPath path = entityPath.getString(criteria.getKey());
        if (criteria.getOperation().equals(":")) {
            return path.containsIgnoreCase(criteria.getValue());
        }
        return null;
    }

}
