package ives.agregginvestment.entity;

import jakarta.persistence.*;

import java.util.UUID;

// CLASSE COMPOSTA DOIS IDs

@Embeddable
// @Embeddable : indica ao Spring que pode utilizar essa classe como ID da entidade
public class AccountStockId {

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "stock_id")
    private String stockId;

    public AccountStockId() {
    }

    public AccountStockId(UUID accountId, String stockId) {
        this.accountId = accountId;
        this.stockId = stockId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }
}
