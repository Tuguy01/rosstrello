package ru.nsu.kanbanboard.kanbanbackend.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ConfirmationTokenEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;
import ru.nsu.kanbanboard.kanbanbackend.services.UserService;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class FindTokenService {


    public static String findToken(UserService userService) {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserEntity) {
            email = ((UserEntity) principal).getEmail();
        } else {
            email = principal.toString();
        }

        return userService.findTokenByEmail(email);
    }


    public static boolean checkTokenBelongsBoard(String token, BoardEntity entity) {
        Collection<ConfirmationTokenEntity> tokens = entity.getTokens();
        Iterator<ConfirmationTokenEntity> iterator = tokens.stream().iterator();

        while (iterator.hasNext()){
            if (Objects.equals(iterator.next().getToken(), token)){
                return true;
            }
        }
        return false;
    }
}
