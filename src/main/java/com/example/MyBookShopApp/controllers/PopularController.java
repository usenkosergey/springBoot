package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.service.BookService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/popular")
public class PopularController {
    /*     Magic number       */
    private final int LIMIT_BOOKS_POPULAR_PAGE = 20;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);
    private final BookService bookService;

    @Autowired
    public PopularController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("popularBooks")
    public List<BookDTO> popularBooks(){
        List<BookEntity> bookEntityList = bookService.getPopularityBooks(0, LIMIT_BOOKS_POPULAR_PAGE).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @GetMapping
    public String popular() {
        return "/books/popular";
    }
}
