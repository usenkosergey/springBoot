package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.entity.tag.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    @Query(nativeQuery = true,
    value = "SELECT tags.id, tags.tag, count(*) AS count_tag " +
            "FROM book2tags AS b2t " +
            "JOIN tags ON b2t.tag_id = tags.id " +
            "GROUP BY tags.id, tags.tag " +
            "ORDER BY count_tag DESC " +
            "LIMIT 50")
    List<Object[]> tagsForIndexPage();


//    SELECT tags.tag, count(*) AS count_tag
//    FROM book2tags AS b2t
//    JOIN tags ON b2t.tag_id = tags.id
//    GROUP BY tags.tag
//    ORDER BY count_tag DESC
//    LIMIT 50

}
