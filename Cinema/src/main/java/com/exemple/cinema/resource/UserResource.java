package com.exemple.cinema.resource;

import com.exemple.cinema.dto.UserDto;
import com.exemple.cinema.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserResource {
    private final UserService userService;

    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/")
    public UserDto createUser(@RequestBody final UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/user/")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDto getById(@PathVariable final Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/")
    public UserDto updateUser(@RequestBody final UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/user/{id}")
    public List<UserDto> deleteUserById(@PathVariable final Long id) {
        return userService.deleteUser(id);
    }
}
