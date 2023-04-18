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

    //@JoinColumn
    private String writerName;
    private String title;
    private String contents;
    private String password;

    public Board(BoardRequestDto requestDto) {
        this.writerName = requestDto.getWriterName();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }


    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}