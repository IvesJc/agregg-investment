package ives.agregginvestment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

// CLASSE COMPOSTA DOIS IDs

@Embeddable
// @Embeddable : indica ao Spring que pode utilizar essa classe como ID da entidade
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountStockId {

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "stock_id")
    private String stockId;
}
