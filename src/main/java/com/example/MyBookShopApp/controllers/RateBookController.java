package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.request.RateBook;
import com.example.MyBookShopApp.dto.response.ResultDTO;
import com.example.MyBookShopApp.entity.other.RateBookEntity;
import com.example.MyBookShopApp.repository.RateBookRepository;
import com.example.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping()
public class RateBookController {

    private final BookService bookService;
    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final RateBookRepository rateBookRepository;

    @Autowired
    public RateBookController(BookService bookService, RateBookRepository rateBookRepository) {
        this.bookService = bookService;
        this.rateBookRepository = rateBookRepository;
    }

    @PostMapping("/rateBook")
    public ResponseEntity<ResultDTO> saveRateBook(RateBook rateBook,
                                                  @CookieValue(name = "RATE", required = false) String ratingOwner) {

        ResultDTO resultDTO = new ResultDTO();
        if (ratingOwner != null && ratingOwner.length() > 0) {
            RateBookEntity rateBookEntity = new RateBookEntity();
            rateBookEntity.setBookId(rateBook.getBookId());
            rateBookEntity.setRating(rateBook.getValue());
            rateBookEntity.setRatingOwner(Integer.valueOf(ratingOwner));
            int id = rateBookRepository.save(rateBookEntity).getId();
            resultDTO.setResult(id != 0);
        }

        return ResponseEntity.ok().body(resultDTO);
    }

}
