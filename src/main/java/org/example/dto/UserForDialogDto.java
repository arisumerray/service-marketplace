package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserForDialogDto {
    private Integer id;
    private String firstName;
    private String lastName;
}
