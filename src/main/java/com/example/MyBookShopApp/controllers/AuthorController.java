package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
@Api(description = "Это контроллер для работы с авторами - /author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorEntity>> authorsMap() {
        return authorService.getAlphabetAndAuthors();
    }


    @GetMapping("/authors")
    public String authorsPage() {
        return "/authors/index";
    }

//    @ApiOperation("метод возвращает всех авторов и первые буквы их ФИО")
//    @GetMapping("/authors")
//    public Map<String, List<AuthorEntity>> authorsPage() {
//        return authorService.getAlphabetAndAuthors();
//    }


}
