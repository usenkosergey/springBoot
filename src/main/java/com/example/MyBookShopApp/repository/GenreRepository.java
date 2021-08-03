package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<GenreEntity, Integer> {
}
