package org.example.dao.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class SignUpRequest {
    String email;
    String password;
    String phoneNumber;
    Boolean isSeller;
}
