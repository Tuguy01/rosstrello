package ru.nsu.kanbanboard.kanbanbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.entities.CardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.repositories.CardsRepository;

@Service
public class CardsService {
    @Autowired
    CardsRepository cardsRepository;

    public CardEntity createNewCard(int columnID, CardEntity card) {return cardsRepository.insertCardIntoColumn(columnID, card);}

    public CardEntity updateCard(CardEntity card) {
        if (card.getId() != null) {
            return cardsRepository.updateCard(card);
        }
        return null;
    }
    public CardEntity deleteCard(int cardID) {
        return cardsRepository.deleteCard(cardID);
    }
}
