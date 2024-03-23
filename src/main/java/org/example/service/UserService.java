package org.example.service;

import org.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
     List<User> getAllUsers();

     UserDetailsService userDetailsService();

     User getUserById(Integer id);
}
