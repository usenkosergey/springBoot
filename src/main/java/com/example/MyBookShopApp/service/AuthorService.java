package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.Author;
import com.example.MyBookShopApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthor() {
        return authorRepository.findAll();
    }

    public Map<String, List<Author>> getAlphabetAndAuthors() {
        return authorRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy((Author author) -> author.getLastName().substring(0, 1).toUpperCase(Locale.ROOT)));

    }

}
