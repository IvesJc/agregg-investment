package ives.agregginvestment.repository;

import ives.agregginvestment.entity.Account;
import ives.agregginvestment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
