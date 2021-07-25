package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Optional<BookEntity> findById(Integer id);

    Page<BookEntity> findAllByOrderByPubDateDesc(Pageable pageable);

    Page<BookEntity> findByPubDateBetweenOrderByPubDateDesc(Date from, Date to, Pageable pageable);

    Page<BookEntity> findByPubDateBeforeOrderByPubDateDesc(Date to, Pageable pageable);

    Page<BookEntity> findByPubDateAfterOrderByPubDateDesc(Date from, Pageable pageable);

    Page<BookEntity> findAllByOrderByPopularityDesc(Pageable pageable);

}
