package com.banana.bananamint.persistence;

import com.banana.bananamint.domain.Category;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Budget;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

import java.util.Date;
import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.banana.bananamint.persistence"})
@AutoConfigureTestEntityManager
class BudgetJPARepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(BudgetJPARepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BudgetJPARepository jpaRepo;

    @Test
    void dadoBudgetOKentoncesSave() {
        // given
        Category category = new Category(1, "Cat1", "A", "Categoría A", LocalDate.now());
        Customer customer = new Customer(1L, "cliente 1", "aaaa@email.com", LocalDate.now(), "12345678J");
        Budget aBudget = new Budget(null, customer, category, 10, customer, 1L, 2000L);

        // when
        jpaRepo.save(aBudget);

        System.out.println(aBudget);

        // then
        assertThat(aBudget).isNotNull();
        assertThat(aBudget.getId()).isGreaterThan(0);
    }


    @Test
    void dadoBudgentNOKentoncesExcepcion() {
        Category category = new Category(1, "Cat1", "A", "Categoría A", LocalDate.now());
        Customer customer = new Customer(1L, "cliente 1", "a", LocalDate.now(), "12345678J");
        Budget aBudget = new Budget(null, customer, category, 10, null, 1L, 2000L);

        assertThrows(Exception.class, () -> {
            jpaRepo.save(aBudget);
        });


    }
}