package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.other.RateBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateBookRepository extends JpaRepository<RateBookEntity, Integer> {

    Integer countByBookIdAndRating(Integer bookId, Integer rating);

    //SELECT rating, count(*) AS r_count FROM public.rate_book where book_id = 111 GROUP BY rating, book_id

}
