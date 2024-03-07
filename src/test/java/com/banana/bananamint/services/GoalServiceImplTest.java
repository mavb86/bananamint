package com.banana.bananamint.services;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Goal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GoalServiceImplTest {
    @Autowired
    GoalServiceImpl service;

    @Test
    void dadoGoalOkEntoncesDevueleLista() throws SQLException {
        Customer customer = new Customer(1L);
        Goal goal = new Goal(null,"Objetivo 1","Descripción del objetivo 1",5000.00,"En proceso",LocalDate.now(),customer);
        List<Goal> listGoals = service.add(customer.getId(),goal);
        assertThat(listGoals).isNotNull();
        assertThat(listGoals.size()).isGreaterThan(0);
    }
    @Test
    void dadoGoalNokEntoncesExcepcion(){
        Customer customer = new Customer(1L);
        Goal goal = new Goal(null,"Obj1","Descripción del objetivo 1",5000.00,"En proceso",LocalDate.now(),customer);
        assertThrows(Exception.class, () -> {
            List<Goal> listGoals = service.add(customer.getId(),goal);
        });
    }
}