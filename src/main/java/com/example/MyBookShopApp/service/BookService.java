package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookEntity> getBooksSearch(Integer offset, Integer limit, String searchWord) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByTitleContainingOrDescriptionContaining(searchWord, searchWord, nextPage);
    }

    public int getCountSearchBooks(String searchValue) {
        Optional<Integer> optional = bookRepository.countByTitleContainingOrDescriptionContaining(searchValue, searchValue);
        return optional.orElse(0);
    }

    public Page<BookEntity> getBooksByTag(Integer offset, Integer limit, Integer tagId) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getBooksByTag(tagId, nextPage);
    }

    public Page<BookEntity> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<BookEntity> getPageOfRecentBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByOrderByPubDateDesc(nextPage);
    }

    public Page<BookEntity> getPopularityBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByOrderByPopularityDesc(nextPage);
    }

    public Optional<BookEntity> getBookBySlug(String slug){
        return bookRepository.findBySlug(slug);
    }

    public List<BookEntity> getBooksById(int[] booksId){
        return bookRepository.findByIdIn(booksId);
    }


}
