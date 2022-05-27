package ru.nsu.kanbanboard.kanbanbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.ConfirmationTokenEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.ConfirmationTokenRepository;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    @Autowired
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationTokenEntity token){
        confirmationTokenRepository.save(token);
    }

}
