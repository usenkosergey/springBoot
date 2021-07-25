package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.mappers.BookMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/tags")
public class TagsController {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @GetMapping("/{ID}")
    public String mainPage(@PathVariable(required = false) Integer ID) {
        logger.info("-------" + ID);
        return "tags/index";
    }
}
