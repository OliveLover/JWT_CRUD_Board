package com.sparta.jwtcrud.service;

import com.sparta.jwtcrud.dto.LoginRequestDto;
import com.sparta.jwtcrud.dto.SignupRequestDto;
import com.sparta.jwtcrud.entity.User;
import com.sparta.jwtcrud.jwt.JwtUtil;
import com.sparta.jwtcrud.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public String signUp(SignupRequestDto requestDto) {
        String userName = requestDto.getUserName();
        String password = requestDto.getPassword();

        if (!Pattern.matches("^[a-z0-9]{4,10}$", userName)) {
            return "형식에 맞지 않는 아이디 입니다.";
        }

        if (!Pattern.matches("^[a-zA-Z0-9]{8,15}$", password)) {
            return "형식에 맞지 않는 비밀번호 입니다.";
        }

        //회원 중복 확인
        Optional<User> found = userRepository.findByUserName(userName);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(requestDto);
        userRepository.save(user);
        return "회원가입 완료!";
    }

    @Transactional(readOnly = true)
    public String login(LoginRequestDto requestDto, HttpServletResponse response) {
        String userName = requestDto.getUserName();
        String password = requestDto.getPassword();

        //사용자 확인
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        //비밀번호 확인
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserName()));
        return "로그인 완료!";
    }

}