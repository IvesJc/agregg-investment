package ives.agregginvestment.controller.dto.User;

import ives.agregginvestment.entity.Account;

import java.time.Instant;
import java.util.List;

public record ResponseUserDTO(
        String username,
        String email,
        Instant creationTimestamp,
        Instant updateTimestamp,
        List<Account> accounts
) {
}
