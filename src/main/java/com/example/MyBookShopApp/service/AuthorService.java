package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.dto.Author;
import com.example.MyBookShopApp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return authorRepository.getAuthor();
    }

    public Map<String, List<Author>> getAlphabetAndAuthors() {
        return authorRepository.getAuthor()
                .stream()
                .collect(Collectors.groupingBy((Author author) -> author.getAuthor().substring(0, 1)));
    }

}
