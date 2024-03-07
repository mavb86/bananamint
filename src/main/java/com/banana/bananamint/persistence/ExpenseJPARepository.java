package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseJPARepository extends JpaRepository<Expense, Integer> {
    //public List<Expense> findAll(Long idCustomer) throws SQLException;
    //public List<Expense> findAllByDate(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws SQLException;

    //public Expense save(Expense expense) throws SQLException;
    public List<Expense> findByUser_id(Long idCustomer);
    public List<Expense> findByUser_idAndDueDate(Long idCustomer, LocalDate oneDate);
}
