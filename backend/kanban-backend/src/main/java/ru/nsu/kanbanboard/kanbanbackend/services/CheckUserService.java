package ru.nsu.kanbanboard.kanbanbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.ConfirmationTokenEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.ConfirmationTokenRepository;
import ru.nsu.kanbanboard.kanbanbackend.repositories.UserRepository;

import java.util.ConcurrentModificationException;

@Service
@AllArgsConstructor
public class CheckUserService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public String findToken(String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->  new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        ConfirmationTokenEntity confirmationToken = confirmationTokenRepository.findByUserId(user.getId()).orElseThrow(() -> new UsernameNotFoundException("Token not found"));
        String token = confirmationToken.getToken();
        return token;

    }


}
