package lpnu.service.Impl;

import lpnu.dto.UserDto;
import lpnu.entity.User;
import lpnu.mapper.UserMapper;
import lpnu.repository.UserRepository;
import lpnu.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(final UserDto userDto) {
        final User user = userMapper.toEntity(userDto);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAll() {
        final List<User> all = userRepository.getAll();
        return  all.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(final Long id) {
        return userMapper.toDTO(userRepository.getById(id));
    }

    @Override
    public UserDto updateUser(final UserDto userDto){
        final User user = userMapper.toEntity(userDto);
       return userMapper.toDTO(userRepository.update(user));
    }

    @Override
    public void deleteUserById(final Long id){
        userRepository.deleteUserById(id);
    }
}
