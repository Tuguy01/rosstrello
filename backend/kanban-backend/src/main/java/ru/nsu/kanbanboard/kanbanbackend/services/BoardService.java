package ru.nsu.kanbanboard.kanbanbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.BoardRepository;

@Service
@AllArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardEntity getBoardById(int id) {
        return boardRepository.getById(id);
    }




}
