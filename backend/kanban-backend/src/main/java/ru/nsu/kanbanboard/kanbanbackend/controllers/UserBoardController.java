package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/api/v1/boards/")
public class UserBoardController {


    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    @Autowired
    ConfirmationTokenService tokenService;

    @GetMapping("board/{boardID}")
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable int boardID,  @RequestParam String token) {

        var entity = boardService.getBoardById(boardID);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }

        //String token = FindTokenService.findToken(userService);
        boolean isAuthOk = FindTokenService.checkTokenBelongsBoard(token, entity);
        if (!isAuthOk) {
            return ResponseEntity.status(402).build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardEntity> createBoard(@RequestParam String name, @RequestParam String token,@RequestBody BoardEntity board){


        ConfirmationTokenEntity confirmationToken = tokenService.findByToken(token);
        var newBoard = boardService.createNewBoard(name, board, confirmationToken);

        if (newBoard != null){
            return ResponseEntity.ok(newBoard);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBoard(@RequestParam String token, @RequestParam Integer boardID) {
        var boards = boardService.getAllBoardsByUserToken(token);
        boolean isSuchBoardFound = false;
        for (BoardEntity board : boards) {
            if (board.getId() == boardID) {
                isSuchBoardFound = true;
                boardService.deleteBoard(boardID);
            }
        }
        if (isSuchBoardFound) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping(path = "attach_user")
    public ResponseEntity<?> attachUserToBoard(@RequestParam String token, @RequestParam Integer boardID, @RequestParam Integer userID) {


        return null;
    }
}
