package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.service.NewsService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/news")
public class NewsController {
    /*     Magic number       */
    private final int LIMIT_BOOKS_NEWS_PAGE = 20;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final NewsService newsService;
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @ModelAttribute("newsBooks")
    public List<BookDTO> recentBooks() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        List<BookEntity> bookEntityList = newsService.getPageFromTo(
                formatDate.format(Date.from(ZonedDateTime.now().minusMonths(1).toInstant())),
                formatDate.format(new Date()),
                0,
                LIMIT_BOOKS_NEWS_PAGE
        ).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @GetMapping
    public String news() {
        return "/books/recent";
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksDTO getNextPage(@RequestParam(name = "from", required = false) String from,
                                @RequestParam(name = "to", required = false) String to,
                                @RequestParam("offset") Integer offset,
                                @RequestParam("limit") Integer limit) {
        offset = offset == 0 ? 0 : offset / LIMIT_BOOKS_NEWS_PAGE;

        //todo удалить позже
        //logger.info("from - " + from + " to - " + to + " offset - " + offset + " limit - " + limit);

        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(0);
        List<BookEntity> bookEntityList = null;

        if (!from.isEmpty() && !to.isEmpty()) {
            bookEntityList = newsService.getPageFromTo(from, to, offset, limit).getContent();
        } else if (from.isEmpty() && to.isEmpty()) {
            bookEntityList = newsService.getPage(offset, limit).getContent();
        } else if (from.isEmpty() && !to.isEmpty()){
            bookEntityList = newsService.getPageTo(to, offset, limit).getContent();
        } else if (!from.isEmpty() && to.isEmpty()) {
            bookEntityList = newsService.getPageFrom(from, offset, limit).getContent();
        }

        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));

        return booksDTO;
    }
}
