package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
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
    /*     Magic number       */
    private final int LIMIT_BOOKS_INDEX_PAGE = 6;
    private final int LIMIT_BOOKS_POPULAR_PAGE = 20;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final BookService bookService;

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookDTO> recommendedBooks() {
        List<BookEntity> bookEntityList = bookService.getPageOfRecommendedBooks(0, LIMIT_BOOKS_INDEX_PAGE).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @ModelAttribute("recentBooks")
    public List<BookDTO> recentBooks() {
        List<BookEntity> bookEntityList = bookService.getPageOfRecentBooks(0, LIMIT_BOOKS_INDEX_PAGE).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @ModelAttribute("popularBooks")
    public List<BookDTO> popularBooks() {
        List<BookEntity> bookEntityList = bookService.getPopularityBooks(0, LIMIT_BOOKS_INDEX_PAGE).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksDTO getBookRecent(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(0);

        List<BookEntity> bookEntityList = bookService.getPageOfRecentBooks(offset, limit).getContent();
        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));
        return booksDTO;
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksDTO getBookPopular(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(0);

        offset = offset == 0 && limit != LIMIT_BOOKS_INDEX_PAGE ? 0 : offset / LIMIT_BOOKS_POPULAR_PAGE;

        List<BookEntity> bookEntityList = bookService.getPopularityBooks(offset, limit).getContent();
        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));
        return booksDTO;
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksDTO getBookPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(0);

        List<BookEntity> bookEntityList = bookService.getPageOfRecommendedBooks(offset, limit).getContent();
        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));
        return booksDTO;
    }

}
