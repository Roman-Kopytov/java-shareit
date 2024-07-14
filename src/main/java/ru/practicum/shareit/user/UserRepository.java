package ru.practicum.shareit.user;


import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(long id);

    void deleteById(long id);

    User update(User user);

    User create(User user);

    List<User> getAll();
}
