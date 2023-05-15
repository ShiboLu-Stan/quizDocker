package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Component
public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setId(rs.getInt("id"));
        contact.setContactDate(new Date(rs.getTimestamp("contactDate").getTime()));
        contact.setUserID(rs.getInt("userID"));
        contact.setText(rs.getString("text"));
        return contact;
    }
}
