package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Goal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GoalJPARepositoryTest {
    @Autowired
    GoalJPARepository repo;

    @Test
    void dadoGoalOkEntoncesPersiste(){
        Customer customer = new Customer(1L);
        Goal goal = new Goal(null,"Objetivo 1","Descripción del objetivo 1",5000.00,"En proceso",LocalDate.now(),customer);
        repo.save(goal);
        assertThat(goal.getId()).isNotNull();
    }
    @Test
    void dadoGoalNokEntoncesExcepcion(){
        Customer customer = new Customer(1L);
        Goal goal = new Goal(null,"Obj1","Descripción del objetivo 1",5000.00,"En proceso",LocalDate.now(),customer);
        assertThrows(Exception.class, () -> {
            repo.save(goal);
        });
    }
}