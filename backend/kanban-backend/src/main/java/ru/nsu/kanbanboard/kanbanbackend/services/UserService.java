package ru.nsu.kanbanboard.kanbanbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.ConfirmationTokenEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.ConfirmationTokenRepository;
import ru.nsu.kanbanboard.kanbanbackend.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->  new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(UserEntity user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists){
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(token, LocalDateTime.now(), user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email){
        return userRepository.enableUser(email);
    }

    public String findTokenByEmail(String email){
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->  new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        ConfirmationTokenEntity confirmationToken = confirmationTokenRepository.findByUserId(user.getId()).orElseThrow(() -> new UsernameNotFoundException("Token not found"));
        return confirmationToken.getToken();
    }


}
