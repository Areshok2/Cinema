package lpnu.service;

import lpnu.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    List<UserDto> getAll();

    UserDto getUserById(Long id) throws InterruptedException;

    UserDto updateUser(UserDto userDto);

    void deleteUserById(Long id);
}
