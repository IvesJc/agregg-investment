package ives.agregginvestment.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_billing_address")
public class BillingAddress {

    // Account e BillingAddress compartilham a mesma PK, que fazem relacionamento 1 - 1 entre si

    @Id
    @Column(name = "account_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "account_id")
    @MapsId
    private Account accountId;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

    public BillingAddress() {
    }

    public BillingAddress(UUID id, String street, Integer number) {
        this.id = id;
        this.street = street;
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
