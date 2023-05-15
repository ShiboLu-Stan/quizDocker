package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class ContactDao {
    JdbcTemplate jdbcTemplate;
    ContactRowMapper contactRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactDao(JdbcTemplate jdbcTemplate, ContactRowMapper contactRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.contactRowMapper = contactRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewContact(Contact contact) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "INSERT INTO Contact (contactDate, userID, text) VALUES " +
                "( ?, ?, ?)";

        jdbcTemplate.update(query, formatter.format(new Timestamp(contact.getContactDate().getTime())), contact.getUserID(), contact.getText()) ;
    }

    public List<Contact> getAllContacts() {
        String query = "SELECT * FROM Contact";

        List<Contact> contacts = jdbcTemplate.query(query, contactRowMapper);

        return contacts;
    }

}
