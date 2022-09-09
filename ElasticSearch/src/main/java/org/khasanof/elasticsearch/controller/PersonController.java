package org.khasanof.elasticsearch.controller;

import org.khasanof.elasticsearch.indices.ElasticIndexer;
import org.khasanof.elasticsearch.indices.Indices;
import org.khasanof.elasticsearch.model.Person;
import org.khasanof.elasticsearch.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/person/*")
public class PersonController {

    @Autowired
    protected ElasticIndexer elasticSearchIndexer;
    @Autowired
    private PersonRepository repository;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody Person person) {
        repository.save(person);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> list() {
        return new ResponseEntity<>(StreamSupport.stream(
                repository.findAll().spliterator(), false
        ).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> get(@PathVariable String id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(), HttpStatus.OK);
    }
}
