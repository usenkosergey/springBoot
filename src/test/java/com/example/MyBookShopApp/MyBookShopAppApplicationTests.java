package com.example.MyBookShopApp;

import com.example.MyBookShopApp.dto.response.AuthorsDTO;
import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.book.links.Book2AuthorEntity;
import com.example.MyBookShopApp.mappers.BookMapper;
import com.example.MyBookShopApp.repository.AuthorRepository;
import com.example.MyBookShopApp.repository.Book2AuthorRepository;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.service.AuthorService;
import com.example.MyBookShopApp.service.BookService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

//	@Autowired
//	BookMapper bookMapper;

	private BookMapper mapper = Mappers.getMapper(BookMapper.class);

	@Test
	void contextLoads() {
		//BooksDTO test1 = new BooksDTO(bookService.getPageOfRecommendedBooks(0, 6).getContent());
		//System.out.println("1=======");

		//BooksDTO test3 = new BooksDTO(bookService.getPageOfRecommendedBooks(1, 6).getContent());
		//System.out.println("2=======");

//		AuthorsDTO test4 = new AuthorsDTO(authorService.getPageAuthor(0,6).getContent());
//		System.out.println("3=======");
//
//		AuthorsDTO test5 = new AuthorsDTO(authorService.getPageAuthor(1,6).getContent());
//		System.out.println("4=======");

	}

}
