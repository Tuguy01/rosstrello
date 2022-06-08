package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.entities.*;
import ru.nsu.kanbanboard.kanbanbackend.security.FindTokenService;
import ru.nsu.kanbanboard.kanbanbackend.services.BoardService;
import ru.nsu.kanbanboard.kanbanbackend.services.ConfirmationTokenService;
import ru.nsu.kanbanboard.kanbanbackend.services.UserService;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

@RestController
@RequestMapping
public class UserBoardController {


    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    @Autowired
    ConfirmationTokenService tokenService;

    @GetMapping("/api/v1/boards/board/{boardID}")
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable int boardID) {

        var entity = boardService.getBoardById(boardID);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }

        String token = FindTokenService.findToken(userService);
        Collection<ConfirmationTokenEntity> tokens = entity.getTokens();
        Iterator<ConfirmationTokenEntity> iterator = tokens.stream().iterator();

        while (iterator.hasNext()){
            if (Objects.equals(iterator.next().getToken(), token)){
                return ResponseEntity.ok(entity);
            }
        }

         return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = "/api/v1/boards/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardEntity> createBoard(@RequestParam String name, @RequestBody BoardEntity board){

        String token = FindTokenService.findToken(userService);

        ConfirmationTokenEntity confirmationToken = tokenService.findByToken(token);

        var newBoard = boardService.createNewBoard(name, board, confirmationToken);

        if (newBoard != null){
            return ResponseEntity.ok(newBoard);
        }
        return ResponseEntity.badRequest().build();
    }


}
