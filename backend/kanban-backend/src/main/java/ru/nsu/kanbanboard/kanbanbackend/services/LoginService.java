package ru.nsu.kanbanboard.kanbanbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.UserRepository;

@Service
@AllArgsConstructor
public class LoginService {

    @Autowired
    private final UserRepository userRepository;

    public boolean isUserExists(UserEntity user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }
}
