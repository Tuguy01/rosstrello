package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kanbanboard.kanbanbackend.entities.*;
import ru.nsu.kanbanboard.kanbanbackend.repositories.ConfirmationTokenRepository;
import ru.nsu.kanbanboard.kanbanbackend.services.BoardService;
import ru.nsu.kanbanboard.kanbanbackend.services.ConfirmationTokenService;
import ru.nsu.kanbanboard.kanbanbackend.services.UserService;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
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
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserEntity){
            email = ((UserEntity)principal).getEmail();
        } else {
            email = principal.toString();
        }

        String token = userService.findTokenByEmail(email);
        Collection<ConfirmationTokenEntity> tokens = entity.getTokens();
        Iterator<ConfirmationTokenEntity> iterator = tokens.stream().iterator();

	System.out.println("Current token: " + token);
        while (iterator.hasNext()){
	    String t = iterator.next().getToken();
	    System.out.println(t);
            if (Objects.equals(t, token)){
                return ResponseEntity.ok(entity);
            }
        }

         return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = "/api/v1/boards/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BoardEntity> createBoard(@RequestParam String name, @RequestBody BoardEntity board){
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserEntity){
            email = ((UserEntity)principal).getEmail();
        } else {
            email = principal.toString();
        }

        String token = userService.findTokenByEmail(email);

        ConfirmationTokenEntity confirmationToken = tokenService.findByToken(token);

        var newBoard = boardService.createNewBoard(name, board, confirmationToken);

        if (newBoard != null){
            System.out.println("null");
            return ResponseEntity.ok(newBoard);
        }
        return ResponseEntity.badRequest().build();
    }


}
