package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.dto.response.TagDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.TagService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class TagsController {
    /*     Magic number       */
    private final int LIMIT_BOOKS_TAGS_PAGE = 20;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    private final TagService tagService;
    private final BookService bookService;

    @Autowired
    public TagsController(TagService tagService, BookService bookService) {
        this.tagService = tagService;
        this.bookService = bookService;
    }

    @ModelAttribute("one_tag")
    public TagDTO oneTag(@PathVariable(required = false) Integer id) {
        if (id != null) {
            return tagService.getOneTag(id);
        }
        return null;
    }

    @ModelAttribute("booksForTag")
    public List<BookDTO> booksForTag(@PathVariable(required = false) Integer id) {
        List<BookEntity> bookEntityList = bookService.getBooksByTag(0, LIMIT_BOOKS_TAGS_PAGE, id).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @GetMapping("/tags/{id}")
    public String mainPage(@PathVariable(required = false) Integer id) {
        return "tags/index";
    }

    @GetMapping("/books/tag/{id}")
    @ResponseBody
    public BooksDTO getBooksByTag(@PathVariable(required = false) Integer id,
                                       @RequestParam("offset") Integer offset,
                                       @RequestParam("limit") Integer limit) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(0);

        offset = offset == 0 && limit != LIMIT_BOOKS_TAGS_PAGE ? 0 : offset / LIMIT_BOOKS_TAGS_PAGE;

        List<BookEntity> bookEntityList = bookService.getBooksByTag(offset, limit, id).getContent();
        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));
        return booksDTO;
    }

}
