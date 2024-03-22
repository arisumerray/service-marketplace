package org.example.service;

import org.example.dao.request.SignInRequest;
import org.example.dao.request.SignUpRequest;
import org.example.dao.response.JwtAuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
