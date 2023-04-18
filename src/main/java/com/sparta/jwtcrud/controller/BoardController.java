package com.sparta.jwtcrud.controller;

import com.sparta.jwtcrud.dto.BoardRequestDto;
import com.sparta.jwtcrud.dto.BoardResponseDto;
import com.sparta.jwtcrud.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/save")
    public String saveBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return boardService.saveBoard(requestDto, httpServletRequest);
    }

    @GetMapping("/get")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();
    }

    @GetMapping("/get/{id}")
    public BoardResponseDto selectGetBoard(@PathVariable Long id) {
        return boardService.selectGetBoard(id);
    }

    @PutMapping("/mod/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return boardService.update(id, requestDto, httpServletRequest);
    }

    @DeleteMapping("/del/{id}")
    public String deleteBoard(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return boardService.deleteBoard(id, httpServletRequest);
    }

}