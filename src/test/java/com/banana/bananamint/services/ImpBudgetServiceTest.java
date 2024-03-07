package com.banana.bananamint.services;

import com.banana.bananamint.controller.BudgetController;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.GetMapping;
import com.banana.bananamint.domain.*;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ImpBudgetServiceTest {
    @Autowired
    ImpBudgetService service;

    @Test
    void dadoBudgetOKEntoncesSave() {

        Category category = new Category(1, "Cat1", "A", "Categoría A", LocalDate.now());
        Customer customer = new Customer(1L, "cliente 1", "aaaa@email.com", LocalDate.now(), "12345678J");
        Budget aBudget = new Budget(null, customer, category, 10, customer, 1L, 2000L);
        Budget response = service.add(1L, "Cat1");
        System.out.println("***** response:" + response);

        assertThat(response.getId()).isGreaterThan(0);


    }

    @Test
    void dadoBudgetNOKEntoncesException() {

        Category category = new Category(1, "Cat1", "A", "Categoría A", LocalDate.now());
        Customer customer = new Customer(1L, "cliente 1", "aaaa@email.com", LocalDate.now(), "12");
        Budget aBudget = new Budget(null, customer, category, 10, customer, 1L, 2000L);

        Assertions.assertThrows(RuntimeException.class, () -> {
            //ResponseEntity response = BudgetController.createBudget(aBudget);

        });
    }
}