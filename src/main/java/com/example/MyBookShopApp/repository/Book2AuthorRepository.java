package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.links.Book2AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Book2AuthorRepository extends JpaRepository<Book2AuthorEntity, Integer> {
    List<Book2AuthorEntity> findAllByBookIdOrderBySortIndex(Integer bookId);
}
