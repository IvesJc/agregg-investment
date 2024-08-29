package ives.agregginvestment.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestCreateUserDTO(
        @NotBlank
        String username,
        @NotBlank
        @Email
        String email,
        String password
) {
}
