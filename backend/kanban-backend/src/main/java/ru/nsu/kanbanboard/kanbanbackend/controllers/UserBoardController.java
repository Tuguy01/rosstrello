package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.CardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.BoardRepository;
import ru.nsu.kanbanboard.kanbanbackend.services.BoardService;

@RestController
@RequestMapping("/api/v1/boards/board/{boardID}")
public class UserBoardController {

    @Autowired
    BoardService boardService;
    @GetMapping
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable int boardID) {
        var entity = boardService.getBoardById(boardID);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }


}
