package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
    List<GenreEntity> findByParentIdNull();

    Optional<GenreEntity> findBySlugLike(String slug);

}
