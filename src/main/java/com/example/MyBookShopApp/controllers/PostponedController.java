package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.service.BookService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/postponed")
public class PostponedController {

    private final BookService bookService;
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);
    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Autowired
    public PostponedController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String postponed(@CookieValue(name = "KEPT", required = false) String kept, Model model) {
        if (kept == null || kept.equals("")) {
            model.addAttribute("totalBooks", 0);
        } else {
            int[] bookId = Arrays.stream(kept.split("/")).mapToInt(Integer::valueOf).distinct().toArray();
            List<BookEntity> bookEntityList = bookService.getBooksById(bookId);
            List<BookDTO> bookDTOList = mapper.bookEntityToBookDTO(bookEntityList);
            int totalPrice = 0;
            int totalPriceOld = 0;
            for (BookDTO b : bookDTOList) {
                totalPrice += b.getPrice();
                totalPriceOld += b.getDiscountPrice();
            }
            model.addAttribute("totalBooks", bookEntityList.size());
            model.addAttribute("books", bookDTOList);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalPriceOld", totalPriceOld);
            model.addAttribute("bookIds", Arrays.toString(bookId));
        }
        return "postponed";
    }
}
