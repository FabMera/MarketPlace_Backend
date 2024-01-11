package com.marketplace.backend.users.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "users_marketplace")
public class UserMarketPlace implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String role;
    private String image;
}
