package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneralUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto create(User user) {
        return UserMapper.mapToUserDto(userRepository.create(user));
    }

    @Override
    public UserDto update(User user) {
        getUserFromRepository(user.getId());
        return UserMapper.mapToUserDto(userRepository.update(user));
    }

    @Override
    public UserDto getById(long id) {
        return UserMapper.mapToUserDto(getUserFromRepository(id));
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.getAll().stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    private User getUserFromRepository(long userId) {
        return userRepository.getById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }
}
