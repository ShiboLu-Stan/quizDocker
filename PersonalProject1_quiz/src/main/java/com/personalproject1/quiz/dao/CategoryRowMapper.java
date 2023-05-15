package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();

        category.setId(rs.getInt("id"));
        category.setTimeNeed(rs.getInt("timeNeed"));
        category.setDescription(rs.getString("description"));
        return category;
    }
}
