package org.khasanof.jpaspecification.service;

import org.khasanof.jpaspecification.criteria.BetweenCriteria;
import org.khasanof.jpaspecification.criteria.GenericCriteria;
import org.khasanof.jpaspecification.criteria.KVCriteria;
import org.khasanof.jpaspecification.criteria.SearchCriteria;
import org.khasanof.jpaspecification.entity.Employee;
import org.khasanof.jpaspecification.enums.OrderType;
import org.khasanof.jpaspecification.repository.EmployeeRepository;
import org.khasanof.jpaspecification.specification.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public List<Employee> findAll(String key, String operation, Object value) {
        Assert.notNull(key, "key is must be not null!");
        Assert.notNull(operation, "operation is must be not null!");
        Assert.notNull(value, "value is must be not null!");
        return repository.findAll(new EmployeeSpecification(new SearchCriteria(key, operation, value)));
    }

    public List<Employee> findAll(SearchCriteria searchCriteria, GenericCriteria genericCriteria) {
        Assert.notNull(searchCriteria, "searchCriteria is must be not null!");
        Assert.notNull(genericCriteria, "genericCriteria is must be not null!");
        PageRequest pageRequest;
        if (Objects.isNull(genericCriteria.getKey())) {
            pageRequest = PageRequest.of(genericCriteria.getPage(), genericCriteria.getSize());
        } else if (Objects.isNull(genericCriteria.getDirection())) {
            pageRequest = PageRequest.of(genericCriteria.getPage(), genericCriteria.getSize(), Sort.Direction.ASC, genericCriteria.getKey());
        } else {
            pageRequest = PageRequest.of(genericCriteria.getPage(), genericCriteria.getSize(), genericCriteria.getDirection(), genericCriteria.getKey());
        }
        return repository.findAll(new EmployeeSpecification(searchCriteria), pageRequest).stream().toList();
    }

    public List<Employee> findAll(String key, Integer from, Integer to) {
        Assert.notNull(key, "key is must be not null!");
        Assert.notNull(from, "from is must be not null!");
        Assert.notNull(to, "to is must be not null!");
        return repository.findAll(new EmployeeSpecification.BetweenSearchCriteria(new BetweenCriteria(key, from, to)));
    }

    public List<Employee> findAll(BetweenCriteria betweenCriteria, GenericCriteria genericCriteria) {
        Assert.notNull(betweenCriteria, "betweenCriteria is must be not null!");
        Assert.notNull(genericCriteria, "genericCriteria is must be not null!");
        PageRequest pageRequest;
        if (Objects.isNull(genericCriteria.getKey())) {
            pageRequest = PageRequest.of(genericCriteria.getPage(), genericCriteria.getSize());
        } else if (Objects.isNull(genericCriteria.getDirection())) {
            pageRequest = PageRequest.of(genericCriteria.getPage(), genericCriteria.getSize(), Sort.Direction.ASC, genericCriteria.getKey());
        } else {
            pageRequest = PageRequest.of(genericCriteria.getPage(), genericCriteria.getSize(), genericCriteria.getDirection(), genericCriteria.getKey());
        }
        return repository.findAll(new EmployeeSpecification.BetweenSearchCriteria(betweenCriteria), pageRequest).stream().toList();
    }

    public List<Employee> findAll(SearchCriteria searchCriteria1, SearchCriteria searchCriteria2, OrderType orderType) {
        Assert.notNull(searchCriteria1);
        Assert.notNull(searchCriteria2);
        return switch (orderType) {
            case OR -> repository.findAll(new EmployeeSpecification(searchCriteria1).or(new EmployeeSpecification(searchCriteria2)));
            case AND -> repository.findAll(new EmployeeSpecification(searchCriteria1).and(new EmployeeSpecification(searchCriteria2)));
        };
    }

    public Employee findOne(Integer id) {
        Assert.notNull(id, "id is must be not null!");
        return repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Employee not found");
        });
    }

    public Employee findOne(SearchCriteria searchCriteria) {
        Assert.notNull(searchCriteria, "searchCriteria is must be not null!");
        return repository.findOne(new EmployeeSpecification(searchCriteria)).orElseThrow(() -> {
            throw new RuntimeException("Employee not found");
        });
    }

    public Employee findOne(KVCriteria criteria) {
        Assert.notNull(criteria);
        return repository.findOne(new EmployeeSpecification.EqualToKeyValue(criteria)).orElseThrow(() -> {
            throw new RuntimeException("Employee not found");
        });
    }

    public Employee findOne(BetweenCriteria betweenCriteria) {
        Assert.notNull(betweenCriteria, "betweenCriteria is must be not null!");
        return repository.findOne(new EmployeeSpecification.BetweenSearchCriteria(betweenCriteria)).orElseThrow(() -> {
            throw new RuntimeException("Employee not found");
        });
    }

    public long count() {
        return repository.count();
    }
}
