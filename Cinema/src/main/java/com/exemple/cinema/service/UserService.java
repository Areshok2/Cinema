package com.exemple.cinema.service;

import com.exemple.cinema.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> getAll();

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto userDto);

    List<UserDto> deleteUser(Long id);
}
