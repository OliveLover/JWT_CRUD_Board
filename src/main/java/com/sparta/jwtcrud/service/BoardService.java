package com.sparta.jwtcrud.service;

import com.sparta.jwtcrud.dto.BoardRequestDto;
import com.sparta.jwtcrud.dto.BoardResponseDto;
import com.sparta.jwtcrud.entity.Board;
import com.sparta.jwtcrud.entity.User;
import com.sparta.jwtcrud.jwt.JwtUtil;
import com.sparta.jwtcrud.repository.BoardRepository;
import com.sparta.jwtcrud.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public String saveBoard(BoardRequestDto requestDto, HttpServletRequest httpServletRequest) {
        String token = jwtUtil.resolveToken(httpServletRequest);
        System.out.println(token);
        Claims claims;


        // 토큰이 있는 경우에만 수정
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUserName(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
//            Board board = boardRepository.findById(id).orElseThrow(
//                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//            );
//            if (requestDto.getPassword().equals(board.getPassword())) {
//                boardRepository.save(board);
//                return board.getContents();
//            } else {
//                return "패스워드가 맞지 않습니다.";
//            }
            Board board = new Board(requestDto, user);
            //System.out.println("password : " + board.getPassword());
            System.out.println("writerName : " + board.getWriterName());
            System.out.println("title : " + board.getTitle());
            System.out.println("contents : " + board.getContents());
            boardRepository.save(board);
            return requestDto.getContents();
        } else return "토큰이 없습니다.";
    }

    @Transactional
    public List<BoardResponseDto> getBoard() {
        List<Board> board = boardRepository.findAllByOrderByCreatedAtDesc();
        List<BoardResponseDto> dtos = new ArrayList<>();

        for (Board boards : board) {
            dtos.add(new BoardResponseDto(boards));
        }
        System.out.println("getBoard 안의 값 : " +dtos);
        return dtos;
    }

    @Transactional
    public BoardResponseDto selectGetBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 게시글 입니다.")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public String update(Long id, BoardRequestDto requestDto, HttpServletRequest httpServletRequest) {
        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;

        // 토큰이 있는 경우에만 수정
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUserName(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            );
//            if (requestDto.getPassword().equals(board.getPassword())) {
//                board.update(requestDto);
//                return board.getContents();
//            } else {
//                return "패스워드가 맞지 않습니다.";
//            }
            board.update(requestDto);
            return board.getContents();
        } else return "수정할 수 없습니다.";
    }

    @Transactional
    public String deleteBoard(Long id, HttpServletRequest httpServletRequest) {
        String token = jwtUtil.resolveToken(httpServletRequest);
        Claims claims;
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        // 토큰이 있는 경우에만 수정
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUserName(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

//            Board board = boardRepository.findById(id).orElseThrow(
//                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
//            );

            boardRepository.deleteById(id);
            return "삭제 완료";
        } else {
            return "패스워드가 맞지 않습니다.";
        }
    }

}