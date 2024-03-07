package com.banana.bananamint.controller;


import com.banana.bananamint.exception.BudgetException;
import com.banana.bananamint.domain.Budget;
import com.banana.bananamint.persistence.BudgetJPARepository;
import com.banana.bananamint.persistence.CustomerJPARepository;
import com.banana.bananamint.services.BudgetService;
import com.banana.bananamint.services.ImpBudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping(value = "/budgets")
public class BudgetController {
    @Autowired
    static
    BudgetJPARepository budgetRepo;
    @Autowired
    ImpBudgetService service;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBudget(
            @RequestBody Budget newBudget
    ) {
        newBudget.setId(null);
        budgetRepo.save(newBudget);
        if (newBudget != null && newBudget.getId() > 0) return new ResponseEntity<>(newBudget, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new Exception("No encontrado"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/{cid}")
    public ResponseEntity createBudgetCat(
            @RequestBody Budget newBudget
    ) {

        budgetRepo.save(newBudget);

        if (newBudget != null && newBudget.getId() > 0) return new ResponseEntity<>(newBudget, HttpStatus.CREATED);
        else
            throw new RuntimeException("Alta presupuesto no válida");
    }


}
