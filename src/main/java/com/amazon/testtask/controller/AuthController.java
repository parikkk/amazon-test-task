package com.amazon.testtask.controller;

import com.amazon.testtask.domain.dto.JwtAuthenticationResponse;
import com.amazon.testtask.domain.dto.SignInRequest;
import com.amazon.testtask.domain.dto.SignUpRequest;
import com.amazon.testtask.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentification")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * User sign up
     *
     * @param request SignUpRequest
     * @return JwtAuthenticationResponse token
     */
    @Operation(summary = "User registration")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        log.info("user sign up");
        return authenticationService.signUp(request);
    }

    /**
     * User sign in
     *
     * @param request SignInRequest
     * @return JwtAuthenticationResponse token
     */
    @Operation(summary = "User authorisation")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        log.info("user sign in");
        return authenticationService.signIn(request);
    }
}
