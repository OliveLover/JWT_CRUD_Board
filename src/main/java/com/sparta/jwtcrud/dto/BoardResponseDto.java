package com.sparta.jwtcrud.dto;

import com.sparta.jwtcrud.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String writerName;
    private String title;
    private String contents;

    private LocalDateTime createdAt;        //확인

    public BoardResponseDto(Board board) {
        this.writerName = board.getWriterName();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();  //확인
    }
}