package ru.nsu.kanbanboard.kanbanbackend.dtos.trello;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrelloListDTO {
    private String id;
    private String name;
    private Boolean closed;
    private String idBoard;
    private Integer pos;
    private Boolean subscribed;
}
