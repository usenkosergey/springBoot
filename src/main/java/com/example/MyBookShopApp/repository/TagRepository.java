package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.tag.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

//    SELECT tags.tag, count(*) AS count_tag
//    FROM book2tags AS b2t
//    JOIN book ON book.id = b2t.book_id
//    JOIN tags ON b2t.tag_id = tags.id
//    GROUP BY tags.tag
//    ORDER BY count_tag DESC

}
