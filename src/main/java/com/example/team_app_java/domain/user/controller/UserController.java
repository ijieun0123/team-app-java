package com.example.team_app_java.domain.user.controller;

import com.example.team_app_java.domain.user.dto.request.UserUpdateRequestDto;
import com.example.team_app_java.domain.user.dto.response.UserResponseDto;
import com.example.team_app_java.domain.user.entity.User;
import com.example.team_app_java.domain.user.service.UserService;
import com.example.team_app_java.global.annotation.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 내 정보 조회
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyInfo(@Auth User user) {
        UserResponseDto userInfo = userService.getUserInfo(user.getId());
        return ResponseEntity.ok(userInfo);
    }

    // 내 정보 수정
    @PatchMapping("/me")
    public ResponseEntity<Void> updateMyInfo(@Auth User user, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.updateUser(user.getId(), userUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    // 회원 탈퇴
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyAccount(@Auth User user) {
        userService.deleteUser(user.getId());
        return ResponseEntity.ok().build();
    }
}