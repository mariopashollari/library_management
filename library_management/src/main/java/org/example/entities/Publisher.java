package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;
    private String name;
    private String address;
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
