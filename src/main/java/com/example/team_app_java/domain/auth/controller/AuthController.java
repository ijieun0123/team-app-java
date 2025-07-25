package com.example.team_app_java.domain.auth.controller;

import com.example.team_app_java.domain.auth.dto.request.LoginRequestDto;
import com.example.team_app_java.domain.auth.dto.request.SignUpRequestDto;
import com.example.team_app_java.domain.auth.dto.response.LoginResponseDto;
import com.example.team_app_java.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        authService.signUp(signUpRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto loginResponseDto = authService.login(request);
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}
