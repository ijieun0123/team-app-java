package com.example.team_app_java.domain.auth.service;

import com.example.team_app_java.domain.auth.dto.request.LoginRequestDto;
import com.example.team_app_java.domain.auth.dto.request.SignUpRequestDto;
import com.example.team_app_java.domain.auth.dto.response.LoginResponseDto;
import com.example.team_app_java.domain.user.entity.User;
import com.example.team_app_java.domain.user.repository.UserRepository;
import com.example.team_app_java.global.util.JwtTokenProvider;
import com.example.team_app_java.global.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(SignUpRequestDto signUpRequestDto) {
        User user = User.builder()
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .name(signUpRequestDto.getName())
                .profileImage(signUpRequestDto.getProfileImage())
                .career(signUpRequestDto.getCareer())
                .build();

        userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtTokenProvider.createToken(user.getId());

        return new LoginResponseDto(token, user.getId(), user.getEmail(), user.getName());
    }
}
