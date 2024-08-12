package ives.agregginvestment.dto;

import java.time.Instant;

public record UpdateUserDTO(
        String username,
        String password,
        Instant updateTimestamp
) {
}
