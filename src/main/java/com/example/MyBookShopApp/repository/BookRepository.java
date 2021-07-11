package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
//
//    List<BookEntity> findByTitleContains(String title);
//
//    List<BookEntity> findByPriceBetween(Integer min, Integer max);
//
//    List<BookEntity> findByPriceIs(Integer price);
//
//    @Query("from Book where is_bestseller = true ")
//    List<BookEntity> getBestsellers();
//
//    @Query(value = "SELECT * FROM book WHERE discount = (SELECT MAX(discount) FROM book)", nativeQuery = true)
//    List<BookEntity> getBooksWithMaxDiscount();
}
