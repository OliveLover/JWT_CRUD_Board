package com.sparta.jwtcrud.service;

import com.sparta.jwtcrud.dto.SignupRequestDto;
import com.sparta.jwtcrud.entity.User;
import com.sparta.jwtcrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String signUp(SignupRequestDto requestDto) {
        String userName = requestDto.getUserName();

        //회원 중복 확인
        Optional<User> found = userRepository.findByUserName(userName);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(requestDto);
        userRepository.save(user);
        return "회원가입 완료!";
    }
}