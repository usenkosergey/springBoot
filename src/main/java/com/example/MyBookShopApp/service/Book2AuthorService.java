package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.repository.Book2AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Book2AuthorService {

    private Book2AuthorRepository book2AuthorRepository;

    @Autowired
    public Book2AuthorService(Book2AuthorRepository book2AuthorRepository) {
        this.book2AuthorRepository = book2AuthorRepository;
    }

    public String getOneAuthor(List<AuthorEntity> authorEntities, Integer bookId) {
        System.out.println("---" + bookId);
        List<Book2AuthorEntity> allAuthors = book2AuthorRepository.findAllByBookIdOrderBySortIndex(bookId);
        Book2AuthorEntity ww = allAuthors.get(0);
        int ff = ww.getAuthorId();
        for (AuthorEntity authorEntity : authorEntities) {
            if (authorEntity.getId() == ff) {
                return authorEntity.getName() + " и другие.";
            }
        }
        return "";
    }
}
