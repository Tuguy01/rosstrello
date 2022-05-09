package ru.nsu.kanbanboard.kanbanbackend.repositories;

import org.springframework.stereotype.Repository;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ColumnRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertColumnIntoBoard(int boardID, ColumnEntity column) {
        column.setBoard(entityManager.find(BoardEntity.class, boardID));
        entityManager.persist(column);
    }

    public ColumnEntity updateColumn(ColumnEntity column) {

        var columnInDB = entityManager.find(ColumnEntity.class, column.getId());
        if (columnInDB != null) {
            columnInDB.setName(column.getName());
            entityManager.merge(columnInDB);
        }
        return columnInDB;
    }

    public ColumnEntity deleteColumn(int columnID) {
        var column = entityManager.find(ColumnEntity.class, columnID);
        if (column != null) {
            entityManager.remove(column);
        }
        return column;
    }
}
