package ives.agregginvestment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_billing_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingAddress {

    // Account e BillingAddress compartilham a mesma PK, que fazem relacionamento 1 - 1 entre si

    @Id
    @Column(name = "account_id")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @MapsId
    private Account accountId;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

}
