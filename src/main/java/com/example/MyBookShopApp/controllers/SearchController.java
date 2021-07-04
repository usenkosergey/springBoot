package com.example.MyBookShopApp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @GetMapping(value = {"/", "/{searchValue}"})
    public String search(@PathVariable(required = false) String searchValue) {
        logger.info("------> " + searchValue);
        return "/search/index";
    }
}
