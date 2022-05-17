package ru.nsu.kanbanboard.kanbanbackend.services.importersservices;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.kanbanboard.kanbanbackend.dtos.trello.TrelloBoardDTO;
import ru.nsu.kanbanboard.kanbanbackend.dtos.trello.TrelloCardDTO;
import ru.nsu.kanbanboard.kanbanbackend.dtos.trello.TrelloListDTO;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.CardEntity;
import ru.nsu.kanbanboard.kanbanbackend.entities.ColumnEntity;
import ru.nsu.kanbanboard.kanbanbackend.externalapi.utils.HttpUtils;
import ru.nsu.kanbanboard.kanbanbackend.repositories.BoardRepository;
import ru.nsu.kanbanboard.kanbanbackend.repositories.CardsRepository;
import ru.nsu.kanbanboard.kanbanbackend.repositories.ColumnRepository;

import java.net.URI;
import java.util.*;

@Service
public class ImportFromTrelloService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private CardsRepository cardsRepository;

    //private String formatURLStringForGettingLists = "https://api.trello.com/1/boards/%s/lists?key=%s&token=%s"
    public BoardEntity importBoardFromTrello(String boardID, String key, String token) {
        var board = getTrelloBoardById(boardID, key, token);
        var lists = getListsOfBoard(boardID, key, token);
        var cards = getCardsOfBoard(boardID, key, token);
        var createdBoard = createAndSaveBoard(board, lists, cards);
        return createdBoard;
    }



    @SneakyThrows
    private TrelloBoardDTO getTrelloBoardById(String boardID, String key, String token) {
        String URLString = "https://api.trello.com/1/boards/" + boardID + "?key=" + key + "&token=" + token;
        URI uri = URI.create(URLString);
        var board = HttpUtils.doAndParseGETRequest(uri, TrelloBoardDTO.class).get();
        return board;
    }

    @SneakyThrows
    private List<TrelloListDTO> getListsOfBoard(String boardID, String key, String token) {
        //String URLString = formatURLStringForGettingLists.format()
        String URLString = "https://api.trello.com/1/boards/" + boardID +"/lists?key=" + key + "&token=" + token;
        URI uri = URI.create(URLString);
        var lists = HttpUtils.doAndParseGETRequest(uri, TrelloListDTO[].class).thenApply(array -> Arrays.asList(array)).get();
        return lists;
    }

    @SneakyThrows
    private List<TrelloCardDTO> getCardsOfBoard(String boardID, String key, String token) {
        String URLString = "https://api.trello.com/1/boards/" + boardID +"/cards?key=" + key + "&token=" + token;
        URI uri = URI.create(URLString);
        var cards = HttpUtils.doAndParseGETRequest(uri, TrelloCardDTO[].class).thenApply(array -> Arrays.asList(array)).get();
        return cards;
    }

    private BoardEntity createAndSaveBoard(TrelloBoardDTO trelloBoardDTO, List<TrelloListDTO> lists, List<TrelloCardDTO> cards) {
        var newBoard = new BoardEntity();
        newBoard.setName(trelloBoardDTO.getName());
        newBoard = boardRepository.createNewBoard(newBoard);

        Map<Integer, Integer> cardIdToColumnIdMap = new HashMap<>();

        //O(n*m)!!!
        for(int i = 0; i < lists.size(); i++) {
            for(int j = 0; j < cards.size(); j++) {
                if (cards.get(j).getIdList().equals(lists.get(i).getId())) {
                    cardIdToColumnIdMap.put(j, i);
                }
            }
        }

        List<ColumnEntity> columns = new ArrayList<>(lists.size());
        for(int i = 0; i < lists.size(); i++) {
            var newColumn = new ColumnEntity();
            newColumn.setName(lists.get(i).getName());
            columnRepository.insertColumnIntoBoard(newBoard.getId(), newColumn);
            columns.add(i, newColumn);
        }
        for (int i = 0; i < cards.size(); i++) {
            var newCard = new CardEntity();
            newCard.setName(cards.get(i).getName());
            newCard.setDescription(cards.get(i).getDesc());
            cardsRepository.insertCardIntoColumn(columns.get(cardIdToColumnIdMap.get(i)).getId(), newCard);
        }
        boardRepository.refresh(newBoard);
        return newBoard;
    }

}
