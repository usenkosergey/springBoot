package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.Book;
import com.example.MyBookShopApp.repository.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private JdbcTemplate jdbcTemplate;
    private BookMapper bookMapper;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate,
                          BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books ", bookMapper);
        return new ArrayList<>(books);
    }
}
