package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.ConfirmationTokenRepository;
import ru.nsu.kanbanboard.kanbanbackend.repositories.UserRepository;
import ru.nsu.kanbanboard.kanbanbackend.services.CheckUserService;

@RestController
@RequestMapping("/api/v1/check")
public class CheckUserController {

    @Autowired
    CheckUserService checkUserService;

    @GetMapping
    public String check(){
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserEntity){
            email = ((UserEntity)principal).getEmail();
        } else {
            email = principal.toString();
        }

        return checkUserService.findToken(email);
    }
}
