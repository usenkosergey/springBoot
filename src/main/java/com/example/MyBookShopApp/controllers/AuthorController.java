package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors/")
public class AuthorController {

    @GetMapping("index.html")
    public String authorsPage(Model model){
        return "authors/index";
    }
}
