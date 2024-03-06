package com.banana.bananamint.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.AccountException;
import com.banana.bananamint.exception.CustomerException;
import com.banana.bananamint.persistence.AccountJPARepository;
import com.banana.bananamint.persistence.CustomerJPARepository;
import com.banana.bananamint.persistence.IncomeJPARepository;
import com.banana.bananamint.services.IncomeExpenseService;
import com.banana.bananamint.services.IncomeExpenseServiceC;
import com.banana.bananamint.util.JsonUtil;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.rmi.AccessException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class IncomeExpenseControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IncomeExpenseService incomeExpenseService;
    @MockBean
    private AccountJPARepository accountJPARepository;

    @Test
    void givenIdCustomerAndIDAccount_whenValidIncome_thenResponseOK() throws Exception {
        Customer user = new Customer(1L, "Pepe", "pepe@p.com", LocalDate.of(2000,02,25), "12345678A");

        Account account = new Account(1L, "corriente", LocalDate.of(2020,05,20), 100, 1000, user,  true);
        Mockito.when(accountJPARepository.findById(1L)).thenReturn(Optional.of(account));
        Income incomeIn = new Income(null,user,1000, LocalDate.now(), account, "accepted");
        Income incomeOut = new Income(null,user,1000, LocalDate.now(), account, "accepted");
        incomeOut.setId(1);

        Mockito.when(incomeExpenseService.addIncome(1L,incomeIn)).thenReturn(incomeOut);

        mvc.perform(post("/IncomeExpense/ingresar/1/1")
                        .content(JsonUtil.asJsonString(incomeIn))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

    }
    @Test
    void givenIdCustomerAndIDAccount_whenInvalidIncome_thenResponse400() throws Exception {
        Customer user = new Customer(1L, "Pepe", "pepe@p.com", LocalDate.of(2000,02,25), "12345678A");

        Account account = new Account(1L, "corriente", LocalDate.of(2020,05,20), 100, 1000, user,  true);
        Mockito.when(accountJPARepository.findById(1L)).thenReturn(Optional.of(account));
        Income incomeIn = new Income(null,user,0, LocalDate.now(), account, "accepted");
        Income incomeOut = new Income(null,user,1000, LocalDate.now(), account, "accepted");
        incomeOut.setId(1);

        Mockito.when(incomeExpenseService.addIncome(1L,incomeIn)).thenReturn(incomeOut);

        mvc.perform(post("/IncomeExpense/ingresar/1/1")
                        .content(JsonUtil.asJsonString(incomeIn))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());

    }
    @Test
    void givenIdCustomerAndIDAccount_whenInexistintCustomer_thenResponse404() throws Exception {
        Customer user = new Customer(1L, "Pepe", "pepe@p.com", LocalDate.of(2000,02,25), "12345678A");

        Account account = new Account();
        Mockito.when(accountJPARepository.findById(1L)).thenReturn(Optional.of(account));

        Income incomeIn = new Income(null,user,1000, LocalDate.now(), account, "accepted");

        /*Income incomeOut = new Income(null,user,1000, LocalDate.now(), account, "accepted");
        incomeOut.setId(1);*/

        Mockito.when(incomeExpenseService.addIncome(1L,incomeIn)).thenThrow(CustomerException.class);
        mvc.perform(post("/IncomeExpense/ingresar/1/1")
                        .content(JsonUtil.asJsonString(incomeIn))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());

    }
}