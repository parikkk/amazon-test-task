package com.amazon.testtask.service;

import com.amazon.testtask.domain.dto.JwtAuthenticationResponse;
import com.amazon.testtask.domain.dto.SignInRequest;
import com.amazon.testtask.domain.dto.SignUpRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
