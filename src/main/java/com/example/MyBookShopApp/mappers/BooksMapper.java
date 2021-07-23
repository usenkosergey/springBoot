package com.example.MyBookShopApp.mappers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BooksMapper {

    List<BookDTO> bookDTOList(List<BookEntity> bookEntities);

    BookDTO bookEntityToBookDTO(BookEntity bookEntity);

}
