package ru.nsu.kanbanboard.kanbanbackend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="board_cards")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;


    @ManyToOne
    @JoinColumn(name = "column_id")
    private ColumnEntity column;


    @JsonIgnore
    public ColumnEntity getColumn() {
        return column;
    }

}
