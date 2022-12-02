package org.khasanof.controller;

import org.khasanof.dao.TransactionDAO;
import org.khasanof.entity.TransactionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/transaction/*")
public class TransactionController {

    private final TransactionDAO dao;

    public TransactionController(TransactionDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody TransactionEntity entity) {
        dao.create(entity);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<TransactionEntity>> list() {
        return new ResponseEntity<>(dao.list(), HttpStatus.OK);
    }
}
