package ru.nsu.kanbanboard.kanbanbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ConfirmationTokenEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.BoardRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardEntity getBoardById(int id) {
        return boardRepository.getById(id);
    }

    public BoardEntity createNewBoard(String name, BoardEntity board, ConfirmationTokenEntity confirmationToken){
        return boardRepository.newBoardCreate(name, board, confirmationToken);
    }

    public BoardEntity deleteBoard(int boardID){
        return  boardRepository.deleteBoard(boardID);
    }

    public List<BoardEntity> getAllBoardsByUserToken(String token) {
        return boardRepository.getAllBoardsOfUserByToken(token);
    }

    public void attachTokenToBoard(BoardEntity board, ConfirmationTokenEntity token) {
        boardRepository.attachTokenToBoard(board, token);
    }

}
