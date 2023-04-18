package com.sparta.jwtcrud.controller;

import com.sparta.jwtcrud.dto.BoardRequestDto;
import com.sparta.jwtcrud.dto.BoardResponseDto;
import com.sparta.jwtcrud.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/saveBoard")
    public String saveBoard(@RequestBody BoardRequestDto requestDto){
        return boardService.saveBoard(requestDto);
    }

    @GetMapping("/api/getBoard")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();
    }

    @GetMapping("/api/getBoard/{id}")
    public BoardResponseDto selectGetBoard(@PathVariable Long id) {
        return boardService.selectGetBoard(id);
    }

    @PutMapping("/api/modBoard/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @DeleteMapping("/api/delBoard/{id}")
    public String deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto);
    }

}

// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB