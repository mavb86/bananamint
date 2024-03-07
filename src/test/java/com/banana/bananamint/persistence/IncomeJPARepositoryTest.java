package com.banana.bananamint.persistence;

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

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.banana.bananamint.persistence"})
@AutoConfigureTestEntityManager
class IncomeJPARepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(IncomeJPARepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private IncomeJPARepository incomeJPARepository;

    @Test
    void givenCustomerAnd_AccountWhenValidIncome_ThenSaveOK() {
        Customer c1 = entityManager.find(Customer.class, 1L);
        Account a1 = entityManager.find(Account.class, 1L);
        Income in1 = new Income(null, c1, 1000, LocalDate.now(), a1, "accepted");
        incomeJPARepository.save(in1);
        System.out.println("in1: " + in1);
        assertThat(in1.getId() > 0);
    }

    @Test
    void givenCustomerAndAccount_WhenInvalidIncome_ThenSaveNOK() {
        Customer c1 = entityManager.find(Customer.class, 1L);
        Account a1 = entityManager.find(Account.class, 1L);


        assertThrows(Exception.class, () -> {
            Income in1 = new Income(null, c1,0, LocalDate.now(), a1, "accepted");
            incomeJPARepository.save(in1);
        });
    }
    @Test
    void givenCustomerIdAndDates_WhenGetList_ThenOK() {

        List<Income> incomeList = incomeJPARepository.findByUser_idAndEnterDate(1L, LocalDate.of(2024,02,02));
        System.out.println("incomeList: " +incomeList);
        assertThat(incomeList.size() > 0);
    }
}