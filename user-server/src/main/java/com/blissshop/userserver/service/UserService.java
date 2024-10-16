package com.blissshop.userserver.service;

import com.blissshop.userserver.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);
}
