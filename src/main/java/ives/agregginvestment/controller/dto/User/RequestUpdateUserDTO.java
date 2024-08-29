package ives.agregginvestment.controller.dto.User;

import java.time.Instant;

public record RequestUpdateUserDTO(
        String username,
        String email,
        String password
) {
}
