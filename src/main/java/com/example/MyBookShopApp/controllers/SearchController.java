package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping(value = "/{searchValue}")
    public String search(@PathVariable String searchValue) {
        System.out.println("------>" + searchValue);
        return "/search/index";
    }
}
