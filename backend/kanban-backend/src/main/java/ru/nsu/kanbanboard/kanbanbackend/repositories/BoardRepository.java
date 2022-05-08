package ru.nsu.kanbanboard.kanbanbackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class BoardRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<BoardEntity> getAll() {
        return entityManager.createQuery("from BoardEntity b order by b.id desc", BoardEntity.class).getResultList();
    }

    public BoardEntity getById(int id) {
        return entityManager.find(BoardEntity.class, id);
    }

    public void insertColumnIntoBoard(int boardID, ColumnEntity column) {
        column.setBoard(getById(boardID));
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
