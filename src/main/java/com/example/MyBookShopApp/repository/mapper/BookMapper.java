package com.example.MyBookShopApp.repository.mapper;

import com.example.MyBookShopApp.entity.Author;
import com.example.MyBookShopApp.entity.Book;
import com.example.MyBookShopApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BookMapper implements RowMapper<Book> {

    private final AuthorRepository authorRepository;

    @Autowired
    public BookMapper(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setAuthor(authorRepository.getAuthorsById(resultSet.getInt("author_id")));
        book.setTitle(resultSet.getString("title"));
        book.setPriceOld(resultSet.getString("price_old"));
        book.setPrice(resultSet.getString("price"));
        return book;
    }


}
