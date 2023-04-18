package com.sparta.jwtcrud.dto;

import com.sparta.jwtcrud.entity.Board;
import com.sparta.jwtcrud.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
//    private Long id;
    private String writerName;
    private String title;
    private String contents;

    public BoardResponseDto(Board board, User user) {
//        this.id = board.getId();
        this.writerName = user.getUserName();
        this.title = board.getTitle();
        this.contents = board.getContents();
    }

    public BoardResponseDto(Board board) {
//        this.id = board.getId();
        this.writerName = board.getWriterName();
        this.title = board.getTitle();
        this.contents = board.getContents();
    }
}