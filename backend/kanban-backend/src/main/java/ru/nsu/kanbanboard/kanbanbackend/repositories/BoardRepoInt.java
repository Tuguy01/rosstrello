package ru.nsu.kanbanboard.kanbanbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface BoardRepoInt extends JpaRepository<BoardEntity, Integer> {

    Optional<BoardEntity> findById(Integer id);

}
