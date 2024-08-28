package ives.agregginvestment.repository;

import ives.agregginvestment.entity.AccountStock;
import ives.agregginvestment.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
