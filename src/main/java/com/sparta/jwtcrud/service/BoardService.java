package com.sparta.jwtcrud.service;

import com.sparta.jwtcrud.dto.BoardRequestDto;
import com.sparta.jwtcrud.dto.BoardResponseDto;
import com.sparta.jwtcrud.entity.Board;
import com.sparta.jwtcrud.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public String saveBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return requestDto.getContents();
    }

    @Transactional
    public List<BoardResponseDto> getBoard() {
        List<Board> board = boardRepository.findAllByOrderByCreatedAtDesc();
        List<BoardResponseDto> dtos = new ArrayList<>();
        for (Board boards : board) {
            dtos.add(new BoardResponseDto(boards));
        }
        return dtos;
    }

    @Transactional
    public BoardResponseDto selectGetBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public String update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(requestDto.getPassword().equals(board.getPassword())) {
            board.update(requestDto);
            return board.getContents();
        }
        else {
            return "패스워드가 맞지 않습니다.";
        }
    }

    public String deleteBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(requestDto.getPassword().equals(board.getPassword())) {
            boardRepository.deleteById(id);
            return "삭제 완료";
        }
        else {
            return "패스워드가 맞지 않습니다.";
        }
    }

}