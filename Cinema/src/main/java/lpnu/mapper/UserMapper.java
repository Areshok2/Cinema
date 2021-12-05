package lpnu.mapper;

import lpnu.dto.UserDto;
import lpnu.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDTO(final User user) {
        final UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setNumber(user.getNumber());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public User toEntity(final UserDto userDto){
        final User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setNumber(userDto.getNumber());
        user.setRole(userDto.getRole());

        return user;
    }
}
