package com.example.MyBookShopApp.mappers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.service.Book2AuthorService;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceContext;

@Mapper(componentModel = "spring", uses = Book2AuthorService.class)
public abstract class BookMapper {

//    @PersistenceContext
//    private Book2AuthorService book2AuthorService;

    @BeforeMapping
    void getAuthor(BookEntity bookEntity, @MappingTarget BookDTO bookDTO) {
        if (bookEntity.getAuthors().size() == 1) {
            bookDTO.setAuthor(bookEntity.getAuthors().get(0).getName());
        } else if (bookEntity.getAuthors().size() > 1) {
            //bookDTO.setAuthor(book2AuthorService.getOneAuthor(bookEntity.getAuthors(), bookEntity.getId()));
            //bookDTO.setAuthor(bookEntity.getAuthors().get(0).getName() + "------");
        }
    }

    public abstract BookDTO bookEntityToBookDTO(BookEntity bookEntity);

    //@Mapping(ignore = true, target = "books")
//    AuthorDTO authorEntityToAuthorDTO(AuthorEntity authorEntity);
}
