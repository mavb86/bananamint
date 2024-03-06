package com.banana.bananamint.controller;

import com.banana.bananamint.persistence.CustomerJPARepository;
import com.banana.bananamint.services.GoalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class GoalControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private GoalServiceImpl goalService;
    @MockBean
    private CustomerJPARepository customerRepo;
}