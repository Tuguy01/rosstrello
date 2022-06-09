package ru.nsu.kanbanboard.kanbanbackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;
import ru.nsu.kanbanboard.kanbanbackend.security.PasswordEncoder;
import ru.nsu.kanbanboard.kanbanbackend.services.LoginService;
import ru.nsu.kanbanboard.kanbanbackend.services.UserService;

@RestController
@RequestMapping(path = "api/v1/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    private UserService userService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserEntity user){

        var loginUser = userService.loadUserByUsername(user.getEmail());


        PasswordEncoder passwordEncoder = new PasswordEncoder();
        BCryptPasswordEncoder bCryptPasswordEncoder = passwordEncoder.bCryptPasswordEncoder();

        if (bCryptPasswordEncoder.matches(user.getPassword(), loginUser.getPassword())){
            return ResponseEntity.ok(userService.findTokenByEmail(user.getEmail()));
        }

        return ResponseEntity.badRequest().build();


    }
}
