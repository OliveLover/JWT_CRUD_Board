package com.sparta.jwtcrud.repository;

import com.sparta.jwtcrud.dto.BoardRequestDto;
import com.sparta.jwtcrud.dto.BoardResponseDto;
import com.sparta.jwtcrud.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
    List<BoardResponseDto> findOneById(Long id);
}