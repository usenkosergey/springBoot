package com.example.MyBookShopApp;

import com.example.MyBookShopApp.dto.response.AuthorsDTO;
import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.entity.genre.GenreEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.repository.AuthorRepository;
import com.example.MyBookShopApp.repository.Book2AuthorRepository;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.GenreRepository;
import com.example.MyBookShopApp.service.AuthorService;
import com.example.MyBookShopApp.service.BookService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MyBookShopAppApplicationTests {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    Book2AuthorRepository book2AuthorRepository;

    @Autowired
    GenreRepository genreRepository;

//	@Autowired
//	BookMapper bookMapper;

    private BookMapper mapper = Mappers.getMapper(BookMapper.class);


    @Test
    void selectGenre(){
      Iterable<GenreEntity> test =  genreRepository.findAll();
      for (GenreEntity g : test) {
          System.out.println(g.toString());
      }
        System.out.println("----");
    }
    @Test
    void contextLoads() {

//java.util.Date (n/a)
//        Date eee = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
//        try {
//            eee = ft.parse("2017.01.01");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Pageable nextPage = PageRequest.of(0, 20);
//
//        try {
//            Page<BookEntity> ddd = bookRepository.findByPubDateBetween(ft.parse("2017.01.01"), ft.parse("2018.01.01"), nextPage);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println("-----");
    }

}
