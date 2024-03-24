package org.example;

import org.example.dto.UserDto;
import org.example.dto.UserForDialogDto;
import org.example.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    List<UserDto> toDtos(List<User> users);

    UserForDialogDto toForDialogDto(User user);
}
