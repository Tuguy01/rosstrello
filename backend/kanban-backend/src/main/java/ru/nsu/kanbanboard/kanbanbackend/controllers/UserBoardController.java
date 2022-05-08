package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.BoardRepository;
import ru.nsu.kanbanboard.kanbanbackend.services.BoardService;

@RestController
@RequestMapping("/api/v1/boards/board/{boardID}")
public class UserBoardController {

    @Autowired
    BoardService boardService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BoardEntity getBoardById(@PathVariable int boardID) {
        var entity = boardService.getBoardById(boardID);

        return entity;
    }

    @PostMapping(path = "columns",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createColumn(@PathVariable int boardID, @RequestBody  ColumnEntity column) {
        boardService.createNewColumn(boardID, column);
    }

    @PutMapping(path = "columns", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ColumnEntity> updateColumn(@RequestBody  ColumnEntity column) {
        var updatedColumn = boardService.updateColumn(column);
        if (updatedColumn != null) {
            return ResponseEntity.ok(updatedColumn);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "columns/{columnID}")
    public ResponseEntity<ColumnEntity> deleteColumn(@PathVariable int columnID) {
        var deletedColumn = boardService.deleteColumn(columnID);
        if (deletedColumn != null) {
            return ResponseEntity.ok(deletedColumn);
        }
        return ResponseEntity.notFound().build();
    }

}
