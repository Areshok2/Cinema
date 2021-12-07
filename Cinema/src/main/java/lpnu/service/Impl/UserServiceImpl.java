package lpnu.service.Impl;

import lpnu.dto.UserDto;
import lpnu.entity.User;
import lpnu.exception.ServiceException;
import lpnu.mapper.UserMapper;
import lpnu.repository.UserRepository;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;

    public UserServiceImpl(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(final UserDto userDto) {

        if(userDto.getId() != null){
            throw new ServiceException(400, "User shouldn't have an id ", null);
        }
        final User user = userMapper.toEntity(userDto);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAll() {

        return userRepository.getAll().stream()
                .map(e -> userMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(final Long id) {
        return userMapper.toDTO(userRepository.getById(id));
    }

    @Override
    public UserDto updateUser(final UserDto userDto){

        if(userDto.getId() == null){
            throw new ServiceException(400, "User doesn't have an id ", null);
        }
        return userMapper.toDTO(userRepository.update(userMapper.toEntity(userDto)));
    }

    @Override
    public void deleteUserById(final Long id){
        userRepository.deleteUserById(id);
    }
}
