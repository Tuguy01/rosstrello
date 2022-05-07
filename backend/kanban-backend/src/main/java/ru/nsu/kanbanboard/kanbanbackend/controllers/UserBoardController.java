package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.BoardRepository;
import ru.nsu.kanbanboard.kanbanbackend.services.BoardService;

@RestController
@RequestMapping("/api/v1/boards")
public class UserBoardController {

    @Autowired
    BoardService boardService;
    @GetMapping("{boardID}")
    public BoardEntity getBoardById(@PathVariable int boardID) {
        var entity = boardService.getBoardById(boardID);

        return entity;
    }



}
