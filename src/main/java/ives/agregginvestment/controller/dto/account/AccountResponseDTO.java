package ives.agregginvestment.controller.dto.account;

import jakarta.validation.constraints.NotBlank;

public record AccountResponseDTO(
        String accountId,
        String description
) {
}
