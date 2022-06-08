package ru.nsu.kanbanboard.kanbanbackend.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name="Boards")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ColumnEntity> columns;
    @ManyToMany
    @JoinTable(name = "Tokens_for_Board", joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "token_id", referencedColumnName = "id"))
    private Collection<ConfirmationTokenEntity> tokens;

}
