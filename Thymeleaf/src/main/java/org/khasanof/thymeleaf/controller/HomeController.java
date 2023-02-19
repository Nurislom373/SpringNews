package org.khasanof.thymeleaf.controller;

import org.khasanof.thymeleaf.models.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/19/2023
 * <br/>
 * Time: 1:01 PM
 * <br/>
 * Package: org.khasanof.thymeleaf.controller
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("message", "Nurislom");
        model.addAttribute("execMode", "dev");
        return "views/index";
    }

    @GetMapping("/views/{text}")
    public String views(Model model, @PathVariable String text) {
        var list = List.of(
                new Customer(1, "Nurislom", new Date()),
                new Customer(2, "Anna", new Date()),
                new Customer(3, "Jeck", new Date()),
                new Customer(4, "Dilshod", new Date())
        );

        String longText = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A asperiores eius ex itaque, molestiae odit optio porro quam totam unde.";

        model.addAttribute("text", text);
        model.addAttribute("customers", list);
        model.addAttribute("long_text", longText);
        model.addAttribute("today", Calendar.getInstance());
        model.addAttribute("boom", new Customer(6, "Javohir", new Date()));
        return "views/views";
    }

}
