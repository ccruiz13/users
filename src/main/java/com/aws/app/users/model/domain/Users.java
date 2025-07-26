package com.aws.app.users.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UNIQUE_IDENTIFICATION", columnNames = "identificacion"),
                @UniqueConstraint(name = "UNIQUE_EMAIL", columnNames = "email"),
        }
)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion", nullable = false)
    private String identificacion;
    private String nombre;
    @Column(name = "email", nullable = false)
    private String email;
}
