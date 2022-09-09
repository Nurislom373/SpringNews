package org.khasanof.jpaspecification.specification;

import org.khasanof.jpaspecification.criteria.BetweenCriteria;
import org.khasanof.jpaspecification.criteria.KVCriteria;
import org.khasanof.jpaspecification.criteria.SearchCriteria;
import org.khasanof.jpaspecification.entity.Company;
import org.khasanof.jpaspecification.entity.Employee;
import org.khasanof.jpaspecification.utils.BaseUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;


public class EmployeeSpecification implements Specification<Employee> {

    private SearchCriteria criteria;

    public EmployeeSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThan(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThan(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType().equals(String.class)) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.<String>get(criteria.getKey()), criteria.getValue().toString());
            }
        }
        return null;
    }

    public static class BetweenSearchCriteria implements Specification<Employee> {

        private BetweenCriteria criteria;

        public BetweenSearchCriteria(BetweenCriteria criteria) {
            this.criteria = criteria;
        }

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.between(root.get(criteria.getKey()), criteria.getFrom(), criteria.getTo());
        }
    }

    public static class EqualToKeyValue implements Specification<Employee> {

        private KVCriteria criteria;

        public EqualToKeyValue(KVCriteria criteria) {
            this.criteria = criteria;
        }

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (BaseUtils.isNumber(criteria.getValue())) {
                return criteriaBuilder.equal(root.get(criteria.getKey()), Integer.parseInt(criteria.getValue()));
            }
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }

    public static class joinClassQuery implements Specification<Employee> {

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            Join<Employee, Company> companyJoin = root.join("companyId", JoinType.INNER);
            return null;
        }
    }
}
