package ru.nsu.kanbanboard.kanbanbackend.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequestEntity {
    private final String username;
    private final String email;
    private final String password;
}
