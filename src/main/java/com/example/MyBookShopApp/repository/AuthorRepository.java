package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.dto.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Author> getAuthor() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM author ORDER BY author ASC", (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setAuthor(rs.getString("author"));
            return author;
        });

        return new ArrayList<>(authors);
    }

}
