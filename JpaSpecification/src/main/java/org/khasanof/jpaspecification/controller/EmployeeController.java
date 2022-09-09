package org.khasanof.jpaspecification.controller;

import org.khasanof.jpaspecification.criteria.BetweenCriteria;
import org.khasanof.jpaspecification.criteria.GenericCriteria;
import org.khasanof.jpaspecification.criteria.KVCriteria;
import org.khasanof.jpaspecification.criteria.SearchCriteria;
import org.khasanof.jpaspecification.entity.Employee;
import org.khasanof.jpaspecification.enums.OrderType;
import org.khasanof.jpaspecification.response.Data;
import org.khasanof.jpaspecification.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/employee/*")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ResponseEntity<Data<List<Employee>>> findAll() {
        return new ResponseEntity<>(new Data<>(service.findAll(), service.count()), HttpStatus.OK);
    }

    @RequestMapping(value = "findOne/id={id}", method = RequestMethod.GET)
    public ResponseEntity<Data<Employee>> findOne(@PathVariable Integer id) {
        return new ResponseEntity<>(new Data<>(service.findOne(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "findOne/key={key}&operation={operation}&value={value}", method = RequestMethod.GET)
    public ResponseEntity<Data<Employee>> findOne(@PathVariable String key, @PathVariable String operation, @PathVariable Object value) {
        return new ResponseEntity<>(new Data<>(service.findOne(new SearchCriteria(key, operation, value))), HttpStatus.OK);
    }

    @RequestMapping(value = "findOne/key={key}&value={value}", method = RequestMethod.GET)
    public ResponseEntity<Data<Employee>> findOne(@PathVariable String key, @PathVariable String value) {
        return new ResponseEntity<>(new Data<>(service.findOne(new KVCriteria(key, value))), HttpStatus.OK);
    }

    @RequestMapping(value = "findOne/key={key}&from={from}&to={to}", method = RequestMethod.GET)
    public ResponseEntity<Data<Employee>> findOne(@PathVariable String key, @PathVariable Integer from, @PathVariable Integer to) {
        return new ResponseEntity<>(new Data<>(service.findOne(new BetweenCriteria(key, from, to))), HttpStatus.OK);
    }

    @RequestMapping(value = "findAll/key={key}&operation={operation}&value={value}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<Employee>>> findAll(@PathVariable String key, @PathVariable String operation, @PathVariable Object value) {
        List<Employee> all = service.findAll(key, operation, value);
        return new ResponseEntity<>(new Data<>(all, all.size()), HttpStatus.OK);
    }

    @RequestMapping(value = "findAll/key={key1}&operation={operation1}&value={value1}/type={type}/&key={key2}&operation={operation2}&value={value2}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<Employee>>> findAll(@PathVariable String key1, @PathVariable String operation1, @PathVariable Object value1, @PathVariable String key2, @PathVariable String operation2, @PathVariable Object value2, @PathVariable String type) {
        List<Employee> all = service.findAll(new SearchCriteria(key1, operation1, value1), new SearchCriteria(key2, operation2, value2), OrderType.valueOf(type.toUpperCase(Locale.ROOT)));
        return new ResponseEntity<>(new Data<>(all, all.size()), HttpStatus.OK);
    }

    @RequestMapping(value = "findAll/key={key}&operation={operation}&value={value}/page={page}&size={size}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<Employee>>> findAll(@PathVariable String key, @PathVariable String operation, @PathVariable Object value, @PathVariable Integer page, @PathVariable Integer size) {
        List<Employee> all = service.findAll(new SearchCriteria(key, operation, value), new GenericCriteria(page, size));
        return new ResponseEntity<>(new Data<>(all, all.size()), HttpStatus.OK);
    }

    @RequestMapping(value = "findAll/key={key}&operation={operation}&value={value}/page={page}&size={size}&sort={sort}&direction={direction}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<Employee>>> findAll(@PathVariable String key, @PathVariable String operation, @PathVariable Object value, @PathVariable Integer page, @PathVariable Integer size, @PathVariable String sort, @PathVariable String direction) {
        List<Employee> all = service.findAll(new SearchCriteria(key, operation, value), new GenericCriteria(page, size, sort, Sort.Direction.fromString(direction)));
        return new ResponseEntity<>(new Data<>(all, all.size()), HttpStatus.OK);
    }

    @RequestMapping(value = "findAll/key={key}&from={from}&to={to}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<Employee>>> findAll(@PathVariable String key, @PathVariable Integer from, @PathVariable Integer to) {
        List<Employee> all = service.findAll(key, from, to);
        return new ResponseEntity<>(new Data<>(all, all.size()), HttpStatus.OK);
    }

    @RequestMapping(value = "findAll/key={key}&from={from}&to={to}/page={page}&size={size}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<Employee>>> findAll(@PathVariable String key, @PathVariable Integer from, @PathVariable Integer to, @PathVariable Integer page, @PathVariable Integer size) {
        List<Employee> all = service.findAll(new BetweenCriteria(key, from, to), new GenericCriteria(size, page));
        return new ResponseEntity<>(new Data<>(all, all.size()), HttpStatus.OK);
    }

    @RequestMapping(value = "findAll/key={key}&from={from}&to={to}/page={page}&size={size}&sort={sort}&direction={direction}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<Employee>>> findAll(@PathVariable String key, @PathVariable Integer from, @PathVariable Integer to, @PathVariable Integer page, @PathVariable Integer size, @PathVariable String sort, @PathVariable String direction) {
        List<Employee> all = service.findAll(new BetweenCriteria(key, from, to), new GenericCriteria(size, page, sort, Sort.Direction.fromString(direction)));
        return new ResponseEntity<>(new Data<>(all, all.size()), HttpStatus.OK);
    }
}
