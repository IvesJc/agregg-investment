package ives.agregginvestment.controller.dto.User;

public record RequestCreateUserDTO(
        String username,
        String email,
        String password
) {
}
