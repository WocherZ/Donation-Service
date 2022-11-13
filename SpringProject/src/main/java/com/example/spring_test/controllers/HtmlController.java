package com.example.spring_test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.GeneratedValue;
import java.util.Map;

@Controller
public class HtmlController {
    @GetMapping("/home")
    public ModelAndView home(Map<String, Object> model) {
        // ...
        return new ModelAndView("home", model);
    }

    @GetMapping("/donation/{id}")
    public ModelAndView donation_forms(Map<String, Object> model) {
        // ...
        return new ModelAndView("donation_forms", model);
    }

    @GetMapping("/registration")
    public ModelAndView registration(Map<String, Object> model) {
        // ...
        return new ModelAndView("registration", model);
    }

    @GetMapping("/login")
    public ModelAndView login(Map<String, Object> model) {
        // ...
        return new ModelAndView("login", model);
    }

    @GetMapping("/profile")
    public ModelAndView profile(Map<String, Object> model) {
        // ...
        return new ModelAndView("profile", model);
    }

    @GetMapping("/options")
    public ModelAndView options(Map<String, Object> model) {
        // ...
        return new ModelAndView("options", model);
    }

    @GetMapping("/analytics")
    public ModelAndView analytics(Map<String, Object> model) {
        // ...
        return new ModelAndView("analytics", model);
    }

    @GetMapping("/out")
    public ModelAndView logout(Map<String, Object> model) {
        return new ModelAndView("logout", model);
    }

    @GetMapping("/user/{id}")
    public ModelAndView user(Map<String, Object> model) {
        return new ModelAndView("user", model);
    }
}
