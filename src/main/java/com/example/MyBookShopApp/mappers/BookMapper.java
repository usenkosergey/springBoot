package com.example.MyBookShopApp.mappers;

import com.example.MyBookShopApp.dto.response.BookDTO;
import com.example.MyBookShopApp.entity.book.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    //BookDTO bookEntityToBookDTO(BookEntity bookEntity);
}
