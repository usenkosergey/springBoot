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
		BooksDTO test1 = new BooksDTO(bookService.getPageOfRecommendedBooks(0, 6).getContent());
		System.out.println("1=======");

		BooksDTO test3 = new BooksDTO(bookService.getPageOfRecommendedBooks(1, 6).getContent());
		System.out.println("2=======");

		AuthorsDTO test4 = new AuthorsDTO(authorService.getPageAuthor(0,6).getContent());
		System.out.println("3=======");

		AuthorsDTO test5 = new AuthorsDTO(authorService.getPageAuthor(1,6).getContent());
		System.out.println("4=======");

	}

	@Test
	void getAuthors(){
		long start1 = System.currentTimeMillis();
		List<Book2AuthorEntity> test1 = book2AuthorRepository.findAllByBookIdOrderBySortIndex(6);
		System.out.println("1=======" + (System.currentTimeMillis() - start1));

		long start = System.currentTimeMillis();
		List<Book2AuthorEntity> test = book2AuthorRepository.findAllByBookIdOrderBySortIndex(5);
		System.out.println("2=======" + (System.currentTimeMillis() - start));


	}

	@Test
	void getMapper(){
		BookEntity bookEntity = bookRepository.findById(1).get();
		BookDTO bookDTO = mapper.bookEntityToBookDTO(bookEntity);

		System.out.println("=====");
	}

	@Test
	void saveMetod(){
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBestseller(true);
		bookEntity.setDescription("book 1 test");
		bookEntity.setPubDate(new Date());
		bookEntity.setSlug("slug_test");
		bookEntity.setTitle("title test");
		bookEntity.setPrice(100);
		bookEntity.setDiscount((short) 0);

		AuthorEntity author = new AuthorEntity();
		author.setSlug("author-slug-test");
		author.setName("Lastname Name");

		Book2AuthorEntity book2AuthorEntity = new Book2AuthorEntity();
		book2AuthorEntity.setSortIndex(1);
		book2AuthorEntity.setAuthor(author);
		book2AuthorEntity.setBook(bookEntity);

		book2AuthorRepository.save(book2AuthorEntity);

		System.out.println("+------");
	}

	@Test
	void delMetod(){
book2AuthorRepository.deleteById(15);

		bookRepository.deleteById(14);
		System.out.println("======");
	}


}
