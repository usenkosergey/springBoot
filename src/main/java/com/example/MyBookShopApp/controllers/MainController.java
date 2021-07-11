package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.service.BookService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.logging.Logger;

import java.util.List;


@Controller
public class MainController {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final BookService bookService;

    private BookRepository bookRepository;
    private BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    public MainController(BookService bookService,
                          BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookEntity> recommendedBooks() {
        List<BookEntity> test = bookService.getPageOfRecommendedBooks(0, 6).getContent();
        return test;
    }

    @GetMapping("/")
    public String mainPage() {
        logger.info("------");
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksDTO getBookPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        BooksDTO test = new BooksDTO(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
        return test;
    }

    @GetMapping("/books/dto")
    public BookDTO getTest() {
//        BookDTO bookDTO = mapper.bookEntityToBookDTO(bookRepository.findById(458).get());
//        System.out.println(bookDTO.toString());
//        System.out.println("-----");
        return null;

    }

}
