package ives.agregginvestment.controller.dto.user;

import java.time.Instant;
import java.util.UUID;

public record RespondCreateUserDTO(
        UUID id,
        String username,
        String email,
        Instant creationTimestamp
) {
}
