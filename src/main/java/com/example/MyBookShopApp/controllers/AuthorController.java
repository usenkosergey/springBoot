package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.AuthorDTO;
import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.AuthorsMapper;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.service.AuthorService;
import io.swagger.annotations.Api;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/authors")
@Api(description = "Это контроллер для работы с авторами - /author")
public class AuthorController {
    /*     Magic number       */
    private final int LIMIT_BOOKS_AUTHORS_PAGE = 5;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);
    private final AuthorsMapper authorsMapper = Mappers.getMapper(AuthorsMapper.class);

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorEntity>> authorsMap() {
        return authorService.getAlphabetAndAuthors();
    }

    @GetMapping()
    public String authorsPage() {
        return "/authors/index";
    }

    @GetMapping("slug/{slug}")
    public String oneAuthorsPage(@PathVariable(required = false) String slug, Model model) {
        AuthorEntity authorEntity = authorService.getOneAuthor(slug);
        AuthorDTO authorDTO = authorsMapper.oneAuthorEntityToAuthorsDTO(authorEntity);
        authorDTO.setCountBooks(authorService.getCountBooksByAuthors(authorDTO.getId()));
        model.addAttribute("oneAuthor", authorDTO);

        List<BookEntity> bookEntityList = authorService.getBooksByAuthor(0, LIMIT_BOOKS_AUTHORS_PAGE, authorDTO.getId());
        List<BookDTO> bookDTOList = mapper.bookEntityToBookDTO(bookEntityList);
        model.addAttribute("authorsBooks", bookDTOList);

        return "/authors/slug";

    }

    @GetMapping("/allbooks/{slug}")
    public String allBooksByAuthor(@PathVariable(required = false) String slug, Model model){
        if (slug != null) {
            AuthorEntity authorEntity = authorService.getOneAuthor(slug);
            AuthorDTO authorDTO = authorsMapper.oneAuthorEntityToAuthorsDTO(authorEntity);
            authorDTO.setCountBooks(authorService.getCountBooksByAuthors(authorDTO.getId()));
            model.addAttribute("oneAuthor", authorDTO);

            List<BookEntity> bookEntityList = authorService.getBooksByAuthor(0, Integer.MAX_VALUE, authorDTO.getId());
            List<BookDTO> bookDTOList = mapper.bookEntityToBookDTO(bookEntityList);
            model.addAttribute("authorsBooks", bookDTOList);
        }
        logger.info("------/////");
        return "/authors/allbooks";
    }
}
