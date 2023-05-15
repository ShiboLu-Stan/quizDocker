package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Category;
import com.personalproject1.quiz.domain.User;
import com.personalproject1.quiz.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class UserDao {
    JdbcTemplate jdbcTemplate;
    UserRowMapper userRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDao (JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }



    public void createNewUser(User user) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "INSERT INTO User (createDate, FirstName, LastName, Address, Email, PhoneNumber, Status, Role, username, password) VALUES " +
                "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, formatter.format(new Timestamp(user.getCreateDate().getTime())), user.getFirstName(), user.getLastName(),
                user.getAddress(), user.getEmail(), user.getPhoneNumber(), user.getStatus(),
                user.getRole(), user.getUsername(), user.getPassword());
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM User";

        List<User> users = jdbcTemplate.query(query, userRowMapper);

        return users;
    }

    public User getUserByID(int id){
        String query = "select * from User where id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", id);
        List<User> users = namedParameterJdbcTemplate.query(query, parameterSource,userRowMapper);

        return users.size() == 0 ? null : users.get(0);
    }

    public void changeUserStatus(int userID){
        User user = getUserByID(userID);

        String status = user.getStatus().equals(Status.Active.toString()) ? Status.Suspend.toString(): Status.Active.toString();

        String query = "UPDATE User " +
                "SET Status = :status " +
                "WHERE id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", userID);
        parameterSource.addValue("status", status);
        namedParameterJdbcTemplate.update(query, parameterSource);
    }

}
