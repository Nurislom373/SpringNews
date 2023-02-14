package org.khasanof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }
}
