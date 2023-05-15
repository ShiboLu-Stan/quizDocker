package com.personalproject1.quiz.dao;

import com.personalproject1.quiz.domain.Category;
import com.personalproject1.quiz.domain.UsedQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDao {
    JdbcTemplate jdbcTemplate;
    CategoryRowMapper categoryRowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CategoryDao(JdbcTemplate jdbcTemplate, CategoryRowMapper categoryRowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRowMapper = categoryRowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewCategory(Category category) {
        String query = "INSERT INTO Category (id, timeNeed, description) VALUES " +
                "( ?, ?, ?)";

        jdbcTemplate.update(query,category.getId(), category.getTimeNeed(), category.getDescription());
    }

    public List<Category> getAllCategorys() {
        String query = "SELECT * FROM Category";

        List<Category> categorys = jdbcTemplate.query(query, categoryRowMapper);

        return categorys;
    }

    public Category getCategoryID(int id){
        String query = "select * from Category where id=:identity";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("identity", id);
        List<Category> categories = namedParameterJdbcTemplate.query(query, parameterSource,categoryRowMapper);

        return categories.size() == 0 ? null : categories.get(0);
    }


}
