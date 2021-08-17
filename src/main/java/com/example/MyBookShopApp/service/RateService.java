package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.RateBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class RateService {

    private final RateBookRepository rateBookRepository;
    private final BookRepository bookRepository;
    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Autowired
    public RateService(RateBookRepository rateBookRepository, BookRepository bookRepository) {
        this.rateBookRepository = rateBookRepository;
        this.bookRepository = bookRepository;
    }

    public List<Integer> getRateBookBySlug(String slug) {
        int bookId = bookRepository.findBySlug(slug).get().getId();
        List<Integer> rate = new ArrayList<>();
        int sumAllRate = 0;
        int countRate = 0;
        for (int i = 1; i <= 5; i++) {
            int count = rateBookRepository.countByBookIdAndRating(bookId, i);
            sumAllRate += count * i;
            countRate += count;
            rate.add(count);
        }

        if (sumAllRate == 0) {
            rate.add(0);
        } else {
            rate.add(Math.round((float) sumAllRate / countRate));
        }
        return rate;
    }

    public Boolean showRateLine(String slug, String rateCookie) {
        int bookId = bookRepository.findBySlug(slug).get().getId();
        int ratingOwner = Integer.parseInt(rateCookie);
        return rateBookRepository.countByBookIdAndRatingOwner(bookId, ratingOwner) == 0;
    }
}
