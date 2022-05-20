package ru.nsu.kanbanboard.kanbanbackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.kanbanboard.kanbanbackend.entities.RegistrationRequestEntity;
import ru.nsu.kanbanboard.kanbanbackend.services.RegistrationService;

import javax.servlet.Registration;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    public String register(@RequestBody RegistrationRequestEntity request){
        return registrationService.register(request);
    }

}
