package ru.nsu.kanbanboard.kanbanbackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ConfirmationTokenEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class BoardRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<BoardEntity> getAll() {
        return entityManager.createQuery("from BoardEntity b order by b.id desc", BoardEntity.class).getResultList();
    }

    public List<BoardEntity> getAllBoardsOfUserByToken(String token) {
       return entityManager.createQuery("Select b.id, b.name from BoardEntity b where b.tokens = :tokenvalue", BoardEntity.class).setParameter("tokenvalue", token).getResultList();

    }

    public BoardEntity getById(int id) {
        return entityManager.find(BoardEntity.class, id);
    }

    public BoardEntity newBoardCreate(String name, BoardEntity board, ConfirmationTokenEntity confirmationToken){
        board.setName(name);

        Collection<ConfirmationTokenEntity> tokens = new ArrayList<>();
        tokens.add(confirmationToken);
        board.setTokens(tokens);

        entityManager.persist(board);
        return board;
    }

    public BoardEntity deleteBoard(int boardID){
        var board = entityManager.find(BoardEntity.class, boardID);
        if (board != null){
            entityManager.remove(board);
        }
        return board;
    }


}
