package ru.nsu.kanbanboard.kanbanbackend.controllers.importers;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.services.BoardService;
import ru.nsu.kanbanboard.kanbanbackend.services.importersservices.ImportFromTrelloService;

@RestController
@RequestMapping(path = "api/v1/import/from-trello")
public class ImportFromTrelloController {
    @Autowired
    ImportFromTrelloService service;
    @Autowired
    BoardService boardService;

    @GetMapping
    public ResponseEntity<BoardEntity> importBoardFromTrello(@RequestParam String boardID, @RequestParam String key, @RequestParam String token) {
        var board = service.importBoardFromTrello(boardID, key, token);
        Integer id = board.getId();
        return ResponseEntity.ok(board);
    }
}
