package com.example.MyBookShopApp.repository.mapper;

import com.example.MyBookShopApp.dto.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setAuthor(resultSet.getString("author"));
        book.setTitle(resultSet.getString("title"));
        book.setPriceOld(resultSet.getString("priceOld"));
        book.setPrice(resultSet.getString("price"));
        return book;
    }
}
