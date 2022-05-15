package ru.nsu.kanbanboard.kanbanbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.ColumnRepository;

@Service
public class ColumnService {
    @Autowired
    ColumnRepository columnRepository;

    public ColumnEntity createNewColumn(int boardID, ColumnEntity column) {return columnRepository.insertColumnIntoBoard(boardID, column);}

    public ColumnEntity updateColumn(ColumnEntity column) {
        if (column.getId() != null) {
            columnRepository.updateColumn(column);
        }
        return null;
    }
    public ColumnEntity deleteColumn(int columnID) {
        return columnRepository.deleteColumn(columnID);
    }
}
