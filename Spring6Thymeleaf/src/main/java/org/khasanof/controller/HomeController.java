package org.khasanof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/4/2023
 * <br/>
 * Time: 11:20 AM
 * <br/>
 * Package: org.khasanof.controller
 */
@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }
}
