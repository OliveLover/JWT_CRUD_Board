package com.sparta.jwtcrud.controller;

import com.sparta.jwtcrud.dto.LoginRequestDto;
import com.sparta.jwtcrud.dto.SignupRequestDto;
import com.sparta.jwtcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody SignupRequestDto requestDto) {
        return userService.signUp(requestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        return userService.login(requestDto);
    }
}