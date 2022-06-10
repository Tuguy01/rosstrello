package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.entities.CardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.services.CardsService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/cards")
public class CardsController {

    @Autowired
    CardsService cardsService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardEntity> createColumn(@RequestParam int columnID, @RequestBody CardEntity card) {
        var newCard = cardsService.createNewCard(columnID, card);
        if (newCard != null) {
            return ResponseEntity.ok(newCard);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardEntity> updateCard(@RequestBody CardEntity card) {
        var updatedCard = cardsService.updateCard(card);
        if (updatedCard != null) {
            return ResponseEntity.ok(updatedCard);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<CardEntity> deleteColumn(@RequestParam int cardID) {
        var deletedCard = cardsService.deleteCard(cardID);
        if (deletedCard != null) {
            return ResponseEntity.ok(deletedCard);
        }
        return ResponseEntity.notFound().build();
    }
}
