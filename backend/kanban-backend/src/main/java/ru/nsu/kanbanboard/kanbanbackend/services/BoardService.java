package ru.nsu.kanbanboard.kanbanbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.BoardRepository;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public BoardEntity getBoardById(int id) {
        return boardRepository.getById(id);
    }

    public void createNewColumn(int boardID, ColumnEntity column) {boardRepository.insertColumnIntoBoard(boardID, column);}

    public ColumnEntity updateColumn(ColumnEntity column) {
        if (column.getId() != null) {
            boardRepository.updateColumn(column);
        }
        return null;
    }

    public ColumnEntity deleteColumn(int columnID) {
        return boardRepository.deleteColumn(columnID);
    }
}
