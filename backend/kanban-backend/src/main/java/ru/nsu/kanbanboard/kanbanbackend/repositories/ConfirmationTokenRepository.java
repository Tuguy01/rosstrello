package ru.nsu.kanbanboard.kanbanbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.kanbanboard.kanbanbackend.entities.ConfirmationTokenEntity;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, Long> {

    Optional<ConfirmationTokenEntity> findByToken(String token);

    Optional<ConfirmationTokenEntity> findByUserId(Long user_id);
}
