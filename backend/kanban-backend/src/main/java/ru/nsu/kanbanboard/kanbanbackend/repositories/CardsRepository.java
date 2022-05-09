package ru.nsu.kanbanboard.kanbanbackend.repositories;

import org.springframework.stereotype.Repository;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.CardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CardsRepository {
    @PersistenceContext
    EntityManager entityManager;


    public CardEntity insertCardIntoColumn(int columnID, CardEntity card) {
        var column = entityManager.find(ColumnEntity.class, columnID);
        if (column == null) {
            return null;
        }
        card.setColumn(column);
        entityManager.persist(card);
        return card;
    }

    public CardEntity updateCard(CardEntity card) {

        var cardInDB = entityManager.find(CardEntity.class, card.getId());
        if (cardInDB != null) {
            cardInDB.setName(cardInDB.getName());
            cardInDB.setDescription(card.getDescription());
            entityManager.merge(cardInDB);
        }
        return cardInDB;
    }

    public CardEntity deleteCard(int cardID) {
        var card = entityManager.find(CardEntity.class, cardID);
        if (card != null) {
            entityManager.remove(card);
        }
        return card;
    }
}
