package ives.agregginvestment.controller.dto.User;

import java.time.Instant;

public record ResponseUpdateUserDTO(
        String username,
        String email,
        Instant updateTimestamp
) {
}
