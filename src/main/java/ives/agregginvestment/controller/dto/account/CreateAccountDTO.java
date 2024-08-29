package ives.agregginvestment.controller.dto.account;

public record CreateAccountDTO(
        String description,
        String street,
        Integer number
) {
}
