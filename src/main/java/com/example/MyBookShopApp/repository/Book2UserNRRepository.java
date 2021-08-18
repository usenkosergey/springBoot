package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.links.Book2UserNREntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Book2UserNRRepository extends JpaRepository<Book2UserNREntity, Integer> {
}