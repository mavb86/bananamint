package com.banana.bananamint.services;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Income;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.banana.bananamint.services"})
@AutoConfigureTestEntityManager
class IncomeExpenseServiceCTest {
    @Autowired
    private IncomeExpenseService incomeExpenseService;
    @Autowired
    private EntityManager entityManager;
    @Test
    void givenIdCustomer_WhenValidIncome_ThenAddOK() {
        Account a1 = entityManager.find(Account.class, 1L);
        Double importeCuenta = a1.getBalance();
        Income in1 = new Income(null, null, 1000, LocalDate.now(), a1, "accepted");
        Income inOut = incomeExpenseService.addIncome(1L, in1);
        System.out.println("in1: " + in1);
        assertThat(inOut.getId() > 0);
        assertThat(inOut.getMoneyTo().getBalance()==importeCuenta + in1.getAmount() );
    }

    @Test
    void givenIdCustomer_WhenInvalidIncome_ThenIncomeException() {
        Account a1 = entityManager.find(Account.class, 1L);
        assertThrows(Exception.class, () -> {
            Income in1 = new Income(null, null, 0, LocalDate.now(), a1, "accepted");
            incomeExpenseService.addIncome(1L, in1);
        });
    }

}