package ives.agregginvestment.controller.dto.user;

import java.time.Instant;

public record ResponseUpdateUserDTO(
        String username,
        String email,
        Instant updateTimestamp
) {
}
