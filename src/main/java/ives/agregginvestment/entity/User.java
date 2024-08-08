package ives.agregginvestment.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
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

    public User() {

    }

    public User(Instant creationTimestamp, String email, String password, Instant updateTimestamp, UUID userId, String username) {
        this.creationTimestamp = creationTimestamp;
        this.email = email;
        this.password = password;
        this.updateTimestamp = updateTimestamp;
        this.userId = userId;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
