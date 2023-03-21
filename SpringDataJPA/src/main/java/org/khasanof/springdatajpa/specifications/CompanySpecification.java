package org.khasanof.springdatajpa.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.khasanof.springdatajpa.criteria.CompanyCriteria;
import org.khasanof.springdatajpa.domain.Company;
import org.springframework.data.jpa.domain.Specification;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 2:44 PM
 * <br/>
 * Package: org.khasanof.springdatajpa.specifications
 */
@AllArgsConstructor
public class CompanySpecification implements Specification<Company> {

    private CompanyCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (criteria.getOperation()) {
            case ">" -> {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue());
            }
            case "<" -> {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue());
            }
            case ":" -> {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
