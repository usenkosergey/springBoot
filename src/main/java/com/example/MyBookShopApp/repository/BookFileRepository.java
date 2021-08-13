package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.book.file.BookFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookFileRepository extends JpaRepository<BookFileEntity, Integer> {
    Optional<BookFileEntity> findByHash(String hash);
}
