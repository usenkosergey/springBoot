package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.GenreDTO;
import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.service.GenreService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class GenresController {

    /*     Magic number       */
    private final int LIMIT_BOOKS_GENRE_PAGE = 20;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    private final GenreService genreService;

    @Autowired
    public GenresController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String genresPage(Model model) {
        List<GenreEntity> genreEntityList = genreService.getAllGenres();
        model.addAttribute("allGenres", genreEntityList);
        return "/genres/index";
    }

    @GetMapping("/genres/{slug}")
    public String getGenreSlug(@PathVariable(required = false) String slug, Model model) {
        GenreDTO genreDTO = new GenreDTO();
        if (slug == null) {
            return "/genres/slug";
        }
        GenreEntity genreEntity = genreService.getOneGenreBySlug(slug).get();
        genreDTO.setId(genreEntity.getId());
        genreDTO.setSlug(genreEntity.getSlug());
        genreDTO.setName(genreEntity.getName());
        genreDTO.setCountBooks(genreEntity.getBooks().size());
        if (genreEntity.getParentId() != null) {
            GenreEntity genre = genreService.getOneGenreById(genreEntity.getParentId().getId()).get();
            genreDTO.setParentName(genre.getName());
            genreDTO.setParentSlug(genre.getSlug());
        }
        model.addAttribute("genreOne", genreDTO);

        List<BookDTO> booksForGenre = new ArrayList<>();
        if (genreEntity.getBooks().size() > LIMIT_BOOKS_GENRE_PAGE) {
            for (int i = 0; i < LIMIT_BOOKS_GENRE_PAGE - 1; i++) {
                booksForGenre.add(mapper.oneBookEntityToBookDTO(genreEntity.getBooks().get(i)));
            }
        } else {
            booksForGenre = mapper.bookEntityToBookDTO(genreEntity.getBooks());
        }

        model.addAttribute("booksForGenre", booksForGenre);

        return "/genres/slug";
    }
}
