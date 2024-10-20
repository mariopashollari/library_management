package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.example.static_data.UserRole;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true, nullable = false)
    private String username;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    @OneToMany(mappedBy = "user")
    private List<Request> requests;
}
