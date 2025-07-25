package com.example.team_app_java.domain.user.service;

import com.example.team_app_java.domain.user.dto.request.UserUpdateRequestDto;
import com.example.team_app_java.domain.user.dto.response.UserResponseDto;
import com.example.team_app_java.domain.user.entity.User;
import com.example.team_app_java.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto getUserInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return new UserResponseDto(user);
    }

    @Transactional
    public void updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setName(userUpdateRequestDto.getName());
        user.setEmail(userUpdateRequestDto.getEmail());
        user.setPassword(userUpdateRequestDto.getPassword());
        user.setProfileImage(userUpdateRequestDto.getProfileImage());
        user.setCareer(userUpdateRequestDto.getCareer());

        User updatedUser = userRepository.save(user);
        new UserResponseDto(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}

