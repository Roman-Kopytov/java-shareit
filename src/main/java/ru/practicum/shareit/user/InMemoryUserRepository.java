package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@RequiredArgsConstructor
@Repository
public class InMemoryUserRepository implements UserRepository {

    private final HashMap<Long, User> userRepository;
    private final Set<String> usersEmail;
    private long seq = 0;

    @Override
    public Optional<User> getById(long id) {
        return Optional.ofNullable(userRepository.get(id));
    }

    @Override
    public void deleteById(long id) {
        usersEmail.remove(userRepository.get(id).getEmail());
        userRepository.remove(id);
    }

    @Override
    public User update(User user) {
        User userForUpdate = userRepository.get(user.getId());
        if (user.getEmail() != null) {
            usersEmail.remove(userRepository.get(user.getId()).getEmail());
            checkDuplicateEmail(user.getEmail());
            userForUpdate.setEmail(user.getEmail());
        }
        if (user.getName() != null) {
            userForUpdate.setName(user.getName());
        }
        userRepository.put(user.getId(), userForUpdate);
        return userForUpdate;
    }

    @Override
    public User create(User user) {
        checkDuplicateEmail(user.getEmail());
        user.setId(calculateUserId());
        userRepository.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userRepository.values());
    }

    private Long calculateUserId() {
        return ++seq;
    }

    private void checkDuplicateEmail(String email) {
        if (!usersEmail.contains(email)) {
            usersEmail.add(email);
        } else {
            throw new RuntimeException();
        }
    }

}
