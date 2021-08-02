package com.example.MyBookShopApp.mappers;

import com.example.MyBookShopApp.dto.response.AuthorDTO;
import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.entity.author.AuthorEntity;
import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.book.links.Book2AuthorEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookMapper {


    @AfterMapping
    void getAuthor(BookEntity bookEntity, @MappingTarget BookDTO bookDTO) {
        if (bookEntity.getBook2author().size() == 1) {
            bookDTO.setAuthor(bookEntity.getBook2author().get(0).getAuthor().getName());
        } else {
            List<Book2AuthorEntity> book2AuthorEntities = bookEntity.getBook2author();
            Comparator<Book2AuthorEntity> comparator = (p1, p2) -> p1.getSortIndex() - p2.getSortIndex();
            book2AuthorEntities.sort(comparator);
            bookDTO.setAuthor(book2AuthorEntities.get(0).getAuthor() + " и другие.");
        }

        bookDTO.setDiscountPrice(bookEntity.getPrice() - (bookEntity.getPrice() * bookEntity.getDiscount() / 100));

    }

    public abstract List<BookDTO> bookEntityToBookDTO(List<BookEntity> bookEntity);
}
