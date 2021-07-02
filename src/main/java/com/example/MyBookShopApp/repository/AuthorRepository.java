package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.Author;
import com.example.MyBookShopApp.repository.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private JdbcTemplate jdbcTemplate;
    private AuthorMapper authorMapper;

    @Autowired
    public AuthorRepository(JdbcTemplate jdbcTemplate,
                            AuthorMapper authorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorMapper = authorMapper;
    }

    public List<Author> getAuthor() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", authorMapper);

        return new ArrayList<>(authors);
        //return null;
    }

}
