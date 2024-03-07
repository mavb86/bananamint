package com.banana.bananamint.services;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.exception.IncomeExpenseException;
import com.banana.bananamint.payload.IncomeExpenseComparison;
import com.banana.bananamint.persistence.IncomeJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.banana.bananamint.services"})
@AutoConfigureTestEntityManager
class IncomeExpenseServiceCTest {
    @Autowired
    private IncomeExpenseService incomeExpenseService;
    @Autowired
    private IncomeJPARepository incomeJPARepository;
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
    @Test
    void givenIdCustomerAndRangeDates_WhenGetPerspectives_ThenListOK() {
        List<IncomeExpenseComparison> incomeExpenseComparisonList = incomeExpenseService.getFinancialPerspective(1L, LocalDate.of(2023, 01, 01), LocalDate.of(2024,03,07));
        System.out.println("incomeExpenseComparisonList:" + incomeExpenseComparisonList);

        assertThat(incomeExpenseComparisonList.size() > 0);

    }
    @Test
    void givenIdCustomerAndRangeDates_WhenGetPerspectivesCustomerInvalid_ThenCustomerException() {

        assertThrows(CustomerException.class, () -> {
            incomeExpenseService.getFinancialPerspective(3L, LocalDate.of(2024, 01, 01), LocalDate.of(2024,03,07));
        });

    }
    @Test
    void givenIdCustomerAndRangeDates_WhenGetPerspectivesCustomerInvalid_ThenIncomeExpenseException() {

        assertThrows(IncomeExpenseException.class, () -> {
            incomeExpenseService.getFinancialPerspective(1L, LocalDate.of(2023, 01, 01), LocalDate.of(2023,03,07));
        });

    }
    @Test
    void acumularIncome () {

        IncomeExpenseServiceC incomeExpenseServiceC_2 = new IncomeExpenseServiceC();
        List<Income> listIncome = incomeJPARepository.findByUser_idAndEnterDate(1L, LocalDate.of(2024,02,02));
        Income income = incomeExpenseServiceC_2.acumularIncome(listIncome);
        System.out.println("incomeSalida: "+ income);

    }

}