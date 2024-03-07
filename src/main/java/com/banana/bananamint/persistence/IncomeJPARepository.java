package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncomeJPARepository extends JpaRepository<Income, Integer> {
    //public List<Income> findAll(Long idCustomer) throws SQLException;

    //public Income save(Income income) throws SQLException;
    public List<Income> findByUser_id(Long idCustomer);
    public List<Income> findByUser_idAndEnterDateBetween(Long idCustomer, LocalDate initDate, LocalDate finalDate);
}
