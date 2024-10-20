package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.example.static_data.RequestStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;
    private String note;
    @Enumerated(value = EnumType.STRING)
    private RequestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime returnedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "requests")
    private List<Book> books;
}
