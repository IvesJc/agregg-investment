package ives.agregginvestment.controller.dto.Account;

public record CreateAccountDTO(
        String description,
        String street,
        Integer number
) {
}
