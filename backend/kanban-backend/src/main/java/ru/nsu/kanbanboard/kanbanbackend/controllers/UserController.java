package ru.nsu.kanbanboard.kanbanbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.dtos.BoardShortInfoDTO;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.security.FindTokenService;
import ru.nsu.kanbanboard.kanbanbackend.services.BoardService;
import ru.nsu.kanbanboard.kanbanbackend.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @GetMapping(path = "boards")
    public ResponseEntity<List<BoardShortInfoDTO>> getBoardsOfUser(@RequestParam String token) {
        var boards = boardService.getAllBoardsByUserToken(token);
        List<BoardShortInfoDTO> dtos = new ArrayList<>(boards.size());
        boards.forEach(el -> dtos.add(BoardShortInfoDTO.fromEntity(el)));
        return ResponseEntity.ok(dtos);
    }


}
