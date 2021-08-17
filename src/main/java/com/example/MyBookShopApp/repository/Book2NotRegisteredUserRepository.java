package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.links.Book2NotRegisteredUserEntyti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Book2NotRegisteredUserRepository extends JpaRepository<Book2NotRegisteredUserEntyti, Integer> {
}