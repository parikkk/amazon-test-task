package com.amazon.testtask.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Registration request")
public class SignUpRequest {

    @Schema(description = "User Name", example = "Jon")
    @Size(min = 5, max = 50, message = "User name should contain from 5 to 50 characters")
    @NotBlank(message = "User name must not be empty")
    private String username;

    @Schema(description = "email", example = "jondoe@gmail.com")
    @Size(min = 5, max = 255, message = "Email should contain from 5 to 50 characters")
    @NotBlank(message = "Email name must not be empty")
    @Email(message = "Email should be in format user@example.com")
    private String email;

    @Schema(description = "password", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "password should contain from 8 to 255 characters")
    @NotBlank(message = "password must not be empty")
    private String password;
}

