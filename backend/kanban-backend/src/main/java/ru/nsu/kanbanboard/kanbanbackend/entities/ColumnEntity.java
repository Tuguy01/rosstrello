package ru.nsu.kanbanboard.kanbanbackend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name="board_columns")
public class ColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

   @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardEntity> cards;

    @JsonIgnore
    public BoardEntity getBoard() {
        return board;
    }


}
