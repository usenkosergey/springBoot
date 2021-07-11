package com.example.MyBookShopApp;

import com.example.MyBookShopApp.dto.response.BooksDTO;
import com.example.MyBookShopApp.repository.AuthorRepository;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyBookShopAppApplicationTests {
	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookService bookService;

	@Test
	void contextLoads() {
		BooksDTO test1 = new BooksDTO(bookService.getPageOfRecommendedBooks(0, 6).getContent());

		System.out.println("1=======");
		BooksDTO test2 = new BooksDTO(bookService.getPageOfRecommendedBooks(0, 6).getContent());
		System.out.println("2=======");

		BooksDTO test3 = new BooksDTO(bookService.getPageOfRecommendedBooks(1, 6).getContent());
		System.out.println("3=======");


	}

}
