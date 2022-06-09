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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/boards/board/{boardID}/{token}")
public class UserBoardController {


    @Autowired
    BoardService boardService;
    @GetMapping
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable int boardID, @PathVariable String token) {

        var entity = boardService.getBoardById(boardID);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        Collection<ConfirmationTokenEntity> tokens = entity.getTokens();
        Iterator<ConfirmationTokenEntity> iterator = tokens.stream().iterator();

	System.out.println("Current token: " + token);
        while (iterator.hasNext()){
	    String t = iterator.next().getToken();
	    System.out.println(t);
            if (Objects.equals(t, token)){
                return ResponseEntity.ok(entity);
            }
        }

         return ResponseEntity.badRequest().build();
    }


}
