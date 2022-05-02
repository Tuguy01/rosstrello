package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/boards")
public class UserBoardController {

    @GetMapping("{boardID}")
    public String getBoardInfo(@PathVariable String boardID) {
        return "Hello on board " + boardID;
    }

}
