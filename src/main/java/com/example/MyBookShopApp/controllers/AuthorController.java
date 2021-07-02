package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.Author;
import com.example.MyBookShopApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/authors/")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap() {
        return authorService.getAlphabetAndAuthors();
    }


    @GetMapping("/authors")
    public String authorsPage() {
        //model.addAttribute("authors", authorService.getAlphabetAndAuthors());
        return "/authors/index";
    }
}
