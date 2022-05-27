package ru.nsu.kanbanboard.kanbanbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.kanbanboard.kanbanbackend.entities.RegistrationRequestEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserRoleEntity;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final UserService userService;

    @Autowired
    private final EmailValidatorService emailValidatorService;

    public String register(RegistrationRequestEntity request){

        boolean isValidEmail = emailValidatorService.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
       }

        String token = userService.signUpUser(
                new UserEntity(request.getEmail(), request.getUsername(), request.getPassword(), UserRoleEntity.USER)
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;


        return token;
    }

}
