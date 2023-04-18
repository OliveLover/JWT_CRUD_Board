package com.sparta.jwtcrud.entity;

import com.sparta.jwtcrud.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "boards")
@Table(name = "boards")
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String writerName;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
//    @Column(nullable = false)
//    private String password;


    public Board(BoardRequestDto requestDto, User user) {
        this.writerName = user.getUserName();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        //this.password = requestDto.getPassword();
    }


    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}