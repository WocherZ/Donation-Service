package com.example.spring_test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/home")
    public ModelAndView home(Map<String, Object> model) {
        model.put("title", "Main page");
        return new ModelAndView("home", model);
    }

}