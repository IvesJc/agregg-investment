package ives.agregginvestment.controller.dto.user;

import ives.agregginvestment.entity.Account;

import java.time.Instant;
import java.util.List;

public record ResponseFindByIdUserDTO(
        String username,
        String email,
        Instant creationTimestamp,
        Instant updateTimestamp,
        List<Account> accounts
) {
}
