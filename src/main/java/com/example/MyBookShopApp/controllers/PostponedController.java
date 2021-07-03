package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/postponed")
public class PostponedController {

    @GetMapping
    public String postponed() {
        return "postponed";
    }
}
