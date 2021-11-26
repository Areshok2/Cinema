package lpnu.resource;

import org.springframework.http.ResponseEntity;
import lpnu.dto.UserDTO;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable final Long id) { return userService.getUserById(id); }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody final UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PutMapping("/users")
    public UserDTO updateUserById(@RequestBody final UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable final Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
