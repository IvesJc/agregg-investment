package ives.agregginvestment.controller.dto.User;

import java.time.Instant;
import java.util.UUID;

public record RespondCreateUserDTO(
        UUID id,
        String username,
        String email,
        Instant creationTimestamp
) {
}
