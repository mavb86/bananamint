package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface GoalJPARepository extends JpaRepository<Goal, Long> {
    public List<Goal> findAllByUser_Id(Long idCustomer) throws SQLException;

    public List<Goal> findByUser_IdAndTargetDateBetween(Long idCustomer, LocalDate initDate, LocalDate finalDate) throws SQLException;
}
