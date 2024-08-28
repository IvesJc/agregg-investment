package ives.agregginvestment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "accountId")
    @PrimaryKeyJoinColumn
    // @PrimaryKeyJoinColumn = Informa que essa também é PK de outra classe
    private BillingAddress billingAddress;

    @OneToMany(mappedBy = "account")
    // mappedBy = Informa que na outra classe já foi feito o mapping
    private List<AccountStock> accountStocks;

}
