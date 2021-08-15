package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.dto.response.TagDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.ResourceStorage;
import com.example.MyBookShopApp.service.TagService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import java.util.List;


@Controller
public class MainController {
    /*     Magic number       */
    private final int LIMIT_BOOKS_INDEX_PAGE = 6;
    private final int LIMIT_BOOKS_POPULAR_PAGE = 20;

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final BookService bookService;

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    private final TagService tagService;
    private final ResourceStorage resourceStorage;
    private final BookRepository bookRepository;

    @Autowired
    public MainController(BookService bookService, TagService tagService, ResourceStorage resourceStorage, BookRepository bookRepository) {
        this.bookService = bookService;
        this.tagService = tagService;
        this.resourceStorage = resourceStorage;
        this.bookRepository = bookRepository;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookDTO> recommendedBooks() {
        List<BookEntity> bookEntityList = bookService.getPageOfRecommendedBooks(0, LIMIT_BOOKS_INDEX_PAGE).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @ModelAttribute("recentBooks")
    public List<BookDTO> recentBooks() {
        List<BookEntity> bookEntityList = bookService.getPageOfRecentBooks(0, LIMIT_BOOKS_INDEX_PAGE).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @ModelAttribute("popularBooks")
    public List<BookDTO> popularBooks() {
        List<BookEntity> bookEntityList = bookService.getPopularityBooks(0, LIMIT_BOOKS_INDEX_PAGE).getContent();
        return mapper.bookEntityToBookDTO(bookEntityList);
    }

    @ModelAttribute("tags")
    public List<TagDTO> tags() {
        List<TagDTO> tagEntityList = tagService.getTagsIndexPage();
        return tagEntityList;
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksDTO getBookRecent(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(0);

        List<BookEntity> bookEntityList = bookService.getPageOfRecentBooks(offset, limit).getContent();
        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));
        return booksDTO;
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksDTO getBookPopular(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(0);

        offset = offset == 0 && limit != LIMIT_BOOKS_INDEX_PAGE ? 0 : offset / LIMIT_BOOKS_POPULAR_PAGE;

        List<BookEntity> bookEntityList = bookService.getPopularityBooks(offset, limit).getContent();
        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));
        return booksDTO;
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksDTO getBookPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setCount(0);

        List<BookEntity> bookEntityList = bookService.getPageOfRecommendedBooks(offset, limit).getContent();
        booksDTO.setBooks(mapper.bookEntityToBookDTO(bookEntityList));
        return booksDTO;
    }

    @GetMapping("/books/{slug}")
    public String bookPage(@PathVariable("slug") String slug, Model model, HttpServletResponse response, @CookieValue(name = "RATE", required = false) String rateCookie) {
        setCookie(response, rateCookie);
        model.addAttribute("book", bookService.getBookBySlug(slug).get());
        return "/books/slug";
    }

    private void setCookie(HttpServletResponse response, String rateCookie) {
        if (rateCookie == null || rateCookie.equals("")) {
            Cookie cookie = new Cookie("RATE", String.valueOf(Long.hashCode(System.currentTimeMillis())));
            cookie.setPath("/");
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
        }
    }

    @PostMapping("/books/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file, @PathVariable("slug") String slug) {
        String savePath = resourceStorage.saveNewBookImage(file, slug);
        BookEntity bookEntity = bookService.getBookBySlug(slug).get();
        bookEntity.setImage(savePath);
        bookRepository.save(bookEntity);

        return ("redirect:/books/" + slug);
    }

    @GetMapping("/books/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {
        Path path = resourceStorage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);

        MediaType mediaType = resourceStorage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType);

        byte[] data = resourceStorage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: " + data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }


}
