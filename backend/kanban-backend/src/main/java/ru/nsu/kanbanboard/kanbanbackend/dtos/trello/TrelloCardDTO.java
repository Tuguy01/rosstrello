package ru.nsu.kanbanboard.kanbanbackend.dtos.trello;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrelloCardDTO {
    private String id;
    private String name;
    private String desc;
    private String idList;
}
