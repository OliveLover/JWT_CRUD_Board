package com.sparta.jwtcrud.controller;

import com.sparta.jwtcrud.dto.BoardRequestDto;
import com.sparta.jwtcrud.dto.BoardResponseDto;
import com.sparta.jwtcrud.entity.Board;
import com.sparta.jwtcrud.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/saveBoard")
    public String saveBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest httpServletRequest){
        //System.out.println("받아오는 값 : " + httpServletRequest);
        System.out.println("contents : " + requestDto.getContents());
        System.out.println("title : " + requestDto.getTitle());
        return boardService.saveBoard(requestDto, httpServletRequest);
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
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return boardService.update(id, requestDto, httpServletRequest);
    }

    @DeleteMapping("/api/delBoard/{id}")
    public String deleteBoard(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return boardService.deleteBoard(id, httpServletRequest);
    }

}

// Client <-Dto-> Controller <-Dto-> Service <-Dto-> Repository <-Entity-> DB