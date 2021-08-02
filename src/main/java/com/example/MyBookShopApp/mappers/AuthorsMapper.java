package com.example.MyBookShopApp.mappers;

import com.example.MyBookShopApp.dto.response.AuthorDTO;
import com.example.MyBookShopApp.entity.author.AuthorEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class AuthorsMapper {


    @AfterMapping
    void setBiographyBlock(AuthorEntity authorEntity, @MappingTarget AuthorDTO authorDTO) {
        if (authorEntity.getDescription().length() > 900) {
            authorDTO.setVisibleBiographyBlock(authorEntity.getDescription().substring(0, authorEntity.getDescription().length() / 2));
            authorDTO.setHiddenBiographyBlock(authorEntity.getDescription().substring(authorEntity.getDescription().length() / 2));
        } else {
            authorDTO.setVisibleBiographyBlock(authorEntity.getDescription());
            authorDTO.setHiddenBiographyBlock("");
        }
    }


    public abstract AuthorDTO oneAuthorEntityToAuthorsDTO(AuthorEntity authorEntity);


}
