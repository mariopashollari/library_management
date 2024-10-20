package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;
    private String firstName;
    private String lastName;
    private String bio;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
