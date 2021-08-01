package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.dto.response.SearchLineDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.service.BookService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    /*     Magic number       */
    private final int LIMIT_BOOKS_SEARCH_PAGE = 20;

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    private final BookService bookService;

    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("search_line")
    public SearchLineDTO searchLine(@PathVariable(required = false) String searchValue) {
        SearchLineDTO searchLineDTO = new SearchLineDTO();
        if (searchValue == null) {
            searchLineDTO.setSearchLine("Строка поиска не задана");
            searchLineDTO.setCountBooks(0);
        } else {
            int countSearchBooks = bookService.getCountSearchBooks(searchValue);
            searchLineDTO.setSearchLine(searchValue);
            searchLineDTO.setCountBooks(countSearchBooks);
        }
        return searchLineDTO;
    }

    @ModelAttribute("booksSearch")
    public List<BookDTO> getSearchBooks(@PathVariable(required = false) String searchValue) {
        if (searchValue != null) {
            List<BookEntity> bookEntityList = bookService.getBooksSearch(0, LIMIT_BOOKS_SEARCH_PAGE, searchValue).getContent();
            return mapper.bookEntityToBookDTO(bookEntityList);
        }
        return null;
    }


    @GetMapping(value = {"/", "/{searchValue}"})
    public String search(@PathVariable(required = false) String searchValue, Model model) {
        return "/search/index";
    }

    @GetMapping("/next/{searchValue}")
    @ResponseBody
    public BooksDTO getNextPageSearch(@PathVariable(required = false) String searchValue,
                                      @RequestParam(value = "offset", required = false) Integer offset,
                                      @RequestParam(value = "limit", required = false) Integer limit) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(bookService.getCountSearchBooks(searchValue));
        offset = offset == 0 && limit != LIMIT_BOOKS_SEARCH_PAGE ? 0 : offset / LIMIT_BOOKS_SEARCH_PAGE;
        List<BookEntity> bookEntityList = bookService.getBooksSearch(offset, limit, searchValue).getContent();
        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));

        return booksDTO;
    }

}
