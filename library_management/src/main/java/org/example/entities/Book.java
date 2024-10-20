package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String description;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    @ManyToMany
    @JoinTable(name = "author_books")
    private List<Author> authors;
    @ManyToMany
    @JoinTable(name = "request_books")
    private List<Request> requests;
}