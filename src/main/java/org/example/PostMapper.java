package org.example;

import org.example.dto.CreatePostDTO;
import org.example.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post fromDto(CreatePostDTO dto);
}
