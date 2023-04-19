package com.sparta.jwtcrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "userName", nullable = false)
    private User userName;

    @ManyToOne
    @JoinColumn(name = "Board", nullable = false)
    private Board board;
}
