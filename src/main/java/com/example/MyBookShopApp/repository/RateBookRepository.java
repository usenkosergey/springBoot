package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.other.RateBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateBookRepository extends JpaRepository<RateBookEntity, Integer> {

    Integer countByBookIdAndRating(Integer bookId, Integer rating);

    Integer countByBookIdAndRatingOwner(Integer bookId, Integer ratingOwner);

}
