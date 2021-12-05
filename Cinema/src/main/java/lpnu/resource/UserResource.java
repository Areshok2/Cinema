package lpnu.resource;

import lpnu.dto.UserDto;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserResource {

    @Autowired
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
    public ResponseEntity deleteUserById(@PathVariable final Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
