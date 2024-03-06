package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface IncomeJPARepository extends JpaRepository<Income, Integer> {
    //public List<Income> findAll(Long idCustomer) throws SQLException;

    //public Income save(Income income) throws SQLException;
}
