package ives.agregginvestment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_account_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountStock {

    @EmbeddedId
    // @EmbeddedId = CHAVE COMPOSTA
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    // @MapsId = Mapea a FK
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;
}
