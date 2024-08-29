package ives.agregginvestment.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestUpdateUserDTO(
        @NotBlank
        String username,
        @Email
        @NotBlank
        String email,
        String password
) {
}
