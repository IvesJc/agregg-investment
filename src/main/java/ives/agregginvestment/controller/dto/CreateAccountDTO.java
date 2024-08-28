package ives.agregginvestment.controller.dto;

public record CreateAccountDTO(
        String description,
        String street,
        Integer number
) {
}
