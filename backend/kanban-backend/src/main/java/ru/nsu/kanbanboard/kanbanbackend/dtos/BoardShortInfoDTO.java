package ru.nsu.kanbanboard.kanbanbackend.dtos;


import lombok.Getter;
import lombok.Setter;
import ru.nsu.kanbanboard.kanbanbackend.entities.BoardEntity;

@Getter
@Setter
public class BoardShortInfoDTO {
    private Integer id;
    private String name;

    public static BoardShortInfoDTO fromEntity(BoardEntity entity) {
        BoardShortInfoDTO dto = new BoardShortInfoDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }
}
