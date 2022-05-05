package ru.nsu.kanbanboard.kanbanbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.kanbanboard.kanbanbackend.entities.UserEntity;

import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api/v1/boards")
public class UserBoardController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @GetMapping("{boardID}")
    public UserEntity getBoardById(@PathVariable int boardID) {
        String testStatementString = "Select * from Users where id = 1";
        List<UserEntity> user = jdbcTemplate.query(testStatementString, BeanPropertyRowMapper.newInstance(UserEntity.class));
        return user.get(0);
    }
}
