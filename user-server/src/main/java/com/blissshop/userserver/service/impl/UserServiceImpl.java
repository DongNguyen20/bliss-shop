package com.blissshop.userserver.service.impl;

import com.blissshop.userserver.model.dto.UserDto;
import com.blissshop.userserver.model.entity.UserEntity;
import com.blissshop.userserver.repository.UserRepository;
import com.blissshop.userserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity newEntity = UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
        return UserDto.toDto(userRepository.save(newEntity));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDto::toDto).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return user != null ? UserDto.toDto(user) : null;
    }
}
