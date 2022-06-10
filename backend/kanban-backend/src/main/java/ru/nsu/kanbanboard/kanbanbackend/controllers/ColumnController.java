package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.services.ColumnService;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/v1/columns")
public class ColumnController {
    @Autowired
    ColumnService columnService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ColumnEntity>  createColumn(@RequestParam int boardID, @RequestBody ColumnEntity column) {

        var newColumn = columnService.createNewColumn(boardID, column);
        if (newColumn != null) {
            return ResponseEntity.ok(newColumn);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ColumnEntity> updateColumn(@RequestBody  ColumnEntity column) {
        var updatedColumn = columnService.updateColumn(column);
        if (updatedColumn != null) {
            return ResponseEntity.ok(updatedColumn);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<ColumnEntity> deleteColumn(@RequestParam int columnID) {
        var deletedColumn = columnService.deleteColumn(columnID);
        if (deletedColumn != null) {
            return ResponseEntity.ok(deletedColumn);
        }
        return ResponseEntity.notFound().build();
    }
}
