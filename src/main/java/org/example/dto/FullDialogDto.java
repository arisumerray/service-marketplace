package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.Message;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class FullDialogDto {
    private Integer id;
    private List<UserForDialogDto> users;
    private List<Message> messages;
}