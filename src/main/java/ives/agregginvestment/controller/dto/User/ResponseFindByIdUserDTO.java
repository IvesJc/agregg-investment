package ives.agregginvestment.controller.dto.User;

import ives.agregginvestment.entity.Account;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ResponseFindByIdUserDTO(
        String username,
        String email,
        Instant creationTimestamp,
        Instant updateTimestamp,
        List<Account> accounts
) {
}
