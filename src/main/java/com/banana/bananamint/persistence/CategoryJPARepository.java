package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryJPARepository {
    public List<Category> findAll() throws SQLException;

    public Category save(Category category) throws SQLException;
}
