package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.author.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    Optional<AuthorEntity> findBySlugLike(String slug);
}
