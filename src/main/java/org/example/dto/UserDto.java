package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.Post;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private List<Post> posts;
}
