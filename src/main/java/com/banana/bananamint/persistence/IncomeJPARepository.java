package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Income;

import java.sql.SQLException;
import java.util.List;

public interface IncomeJPARepository {
    public List<Income> findAll(Long idCustomer) throws SQLException;

    public Income save(Income income) throws SQLException;
}
