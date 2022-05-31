package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.entities.*;
import ru.nsu.kanbanboard.kanbanbackend.services.BoardService;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/boards/board/{boardID}/{token}")
public class UserBoardController {


    @Autowired
    BoardService boardService;
    @GetMapping
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable int boardID, String token) {

        var entity = boardService.getBoardById(boardID);
        System.out.println(boardID);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        Collection<ConfirmationTokenEntity> tokens = entity.getTokens();
        Iterator<ConfirmationTokenEntity> iterator = tokens.stream().iterator();

        while (iterator.hasNext()){
            if (Objects.equals(iterator.next().getToken(), token)){
                return ResponseEntity.ok(entity);
            }
        }

         return ResponseEntity.badRequest().build();
    }


}
