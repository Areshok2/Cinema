package com.exemple.cinema.service.Impl;

import com.exemple.cinema.dto.UserDto;
import com.exemple.cinema.entity.User;
import com.exemple.cinema.mapper.UserMapper;
import com.exemple.cinema.repository.UserRepository;
import com.exemple.cinema.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAll() {
        List<User> all = userRepository.getAll();
        return  all.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.toDTO(userRepository.getById(id));
    }

    @Override
    public UserDto updateUser(UserDto userDto){
        User user = userMapper.toEntity(userDto);
       return userMapper.toDTO(userRepository.update(user));
    }

    @Override
    public List<UserDto> deleteUser(Long id){
        return userRepository.delete(id).stream().map(userMapper::toDTO).collect(Collectors.toList());
    }
}
