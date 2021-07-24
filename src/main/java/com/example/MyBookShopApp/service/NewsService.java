package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@Service
public class NewsService {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    private final BookRepository bookRepository;

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public NewsService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookEntity> getPageFromTo(String from, String to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        Page<BookEntity> resultPage = null;
        try {
            resultPage = bookRepository.findByPubDateBetweenOrderByPubDateDesc(format.parse(from), format.parse(to), nextPage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultPage;

    }

    public Page<BookEntity> getPage(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByOrderByPubDateDesc(nextPage);
    }

    public Page<BookEntity> getPageTo(String to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        Page<BookEntity> resultPage = null;
        try {
            resultPage = bookRepository.findByPubDateBeforeOrderByPubDateDesc(format.parse(to), nextPage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultPage;
    }

    public Page<BookEntity> getPageFrom(String from, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        Page<BookEntity> resultPage = null;
        try {
            resultPage = bookRepository.findByPubDateAfterOrderByPubDateDesc(format.parse(from), nextPage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultPage;
    }

}
