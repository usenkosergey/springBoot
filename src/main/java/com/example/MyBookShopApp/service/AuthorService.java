package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.repository.AuthorRepository;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<AuthorEntity> getAuthor() {
        return authorRepository.findAll();
    }

    public Map<String, List<AuthorEntity>> getAlphabetAndAuthors() {
        return authorRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy((AuthorEntity authorEntity) -> authorEntity.getName().substring(0, 1).toUpperCase(Locale.ROOT)));
    }

    public Page<AuthorEntity> getPageAuthor(Integer offset, Integer limit) {
        Pageable nexPage = PageRequest.of(offset, limit);
        return authorRepository.findAll(nexPage);
    }

    public AuthorEntity getOneAuthor(String slug) {
        if (authorRepository.findBySlugLike(slug).isPresent()) {
            return authorRepository.findBySlugLike(slug).get();
        } else {
            return new AuthorEntity();
        }
    }

    public Integer getCountBooksByAuthors(Integer authorId) {
        Optional<Integer> countBooksByAuthor = bookRepository.countBooksByAuthor(authorId);
        return countBooksByAuthor.orElse(0);
    }

    public List<BookEntity> getBooksByAuthor(Integer offset, Integer limit, Integer authorId){
        Pageable nexPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByAuthor(authorId, nexPage).getContent();
    }

}
