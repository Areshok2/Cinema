package com.exemple.cinema.mapper;

import com.exemple.cinema.dto.UserDto;
import com.exemple.cinema.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setNumber(user.getNumber());

        return userDTO;
    }

    public User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setNumber(userDto.getNumber());

        return user;
    }
}
