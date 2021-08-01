package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<BookEntity, Integer> {

    Optional<BookEntity> findById(Integer id);

    Page<BookEntity> findAllByOrderByPubDateDesc(Pageable pageable);

    Page<BookEntity> findByPubDateBetweenOrderByPubDateDesc(Date from, Date to, Pageable pageable);

    Page<BookEntity> findByPubDateBeforeOrderByPubDateDesc(Date to, Pageable pageable);

    Page<BookEntity> findByPubDateAfterOrderByPubDateDesc(Date from, Pageable pageable);

    Page<BookEntity> findAllByOrderByPopularityDesc(Pageable pageable);

    @Query(nativeQuery = true,
    value = "SELECT * FROM book " +
            "JOIN book2tags ON book.id = book2tags.book_id " +
            "JOIN tags ON tags.id = book2tags.tag_id " +
            "WHERE tags.id = (:tagId)")
    Page<BookEntity> getBooksByTag(@Param("tagId") Integer tagId, Pageable pageable);

    Page<BookEntity> findAllByTitleContainingOrDescriptionContaining(String searchWord, String searchWord1, Pageable pageable);

    Optional<Integer> countByTitleContainingOrDescriptionContaining(String searchWord, String searchWord1);

}
