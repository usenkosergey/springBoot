package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreEntity> getAllGenres(){
        return genreRepository.findByParentIdNull();
    }

    public Optional<GenreEntity> getOneGenreBySlug(String slug) {
        return genreRepository.findBySlugLike(slug);
    }

    public Optional<GenreEntity> getOneGenreById(Integer id){
        return genreRepository.findById(id);
    }



}
