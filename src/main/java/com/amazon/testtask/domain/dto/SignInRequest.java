package com.amazon.testtask.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Authentication request")
public class SignInRequest {

    @Schema(description = "User Name", example = "Jon")
    @Size(min = 5, max = 50, message = "User name should contain from 5 to 50 characters")
    @NotBlank(message = "User name must not be empty")
    private String username;

    @Schema(description = "password", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "password should contain from 8 to 255 characters")
    @NotBlank(message = "password must not be empty")
    private String password;
}