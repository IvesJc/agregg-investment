package ives.agregginvestment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    // Quando a entidade for CREATED, o tempo que isso ocorreu será gravado
    @CreationTimestamp
    private Instant creationTimestamp;

    // Quando a entidade for UPDATED, o tempo que isso ocorreu será gravado
    @UpdateTimestamp
    private Instant updateTimestamp;

    @OneToMany(mappedBy = "user")
    // mappedBy = nome do campo da entity que se conectaram
    private List<Account> accounts;
}
