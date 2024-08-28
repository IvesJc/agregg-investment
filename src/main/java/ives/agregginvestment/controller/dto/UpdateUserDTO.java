package ives.agregginvestment.controller.dto;

import java.time.Instant;

public record UpdateUserDTO(
        String username,
        String password,
        Instant updateTimestamp
) {
}
