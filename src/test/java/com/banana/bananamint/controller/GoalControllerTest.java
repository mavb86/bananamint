package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Customer;
import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.services.GoalServiceImpl;
import com.banana.bananamint.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class GoalControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private GoalServiceImpl goalService;

    @Test
    void dadoObjetivo_cuandoObjetivoOK_thenRespuestaOK() throws Exception {

        Customer customer = new Customer(1L);
        Goal newGoal = new Goal(null, "Objetivo 1", "Descripción del objetivo 1", 5000.00, "En proceso", LocalDate.now(), customer);

        List<Goal> listGoals = new ArrayList();
        Goal salidaGoal = new Goal(1L, "Objetivo 1", "Descripción del objetivo 1", 5000.00, "En proceso", LocalDate.now(), customer);
        listGoals.add(salidaGoal);

        Mockito.when(goalService.add(any(),any())).thenReturn(listGoals);

        mvc.perform(post("/goals/customer/1")
                        .content(JsonUtil.asJsonString(newGoal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void dadoObjetivo_cuandoObjetivoOK_thenRespuesta400() throws Exception {
        Customer customer = new Customer(1L);
        Goal newGoal = new Goal(null,"Obj1","Descripción del objetivo 1",5000.00,"En proceso",LocalDate.now(),customer);

        mvc.perform(post("/goals/customer/1")
                        .content(JsonUtil.asJsonString(newGoal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}