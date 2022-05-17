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

    public BoardEntity createNewBoard(BoardEntity entity) {
        entityManager.persist(entity);
        return entity;
    }
}
