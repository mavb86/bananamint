package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Account;
import com.banana.bananamint.domain.Income;
import com.banana.bananamint.exception.AccountException;
import com.banana.bananamint.persistence.AccountJPARepository;
import com.banana.bananamint.services.IncomeExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/IncomeExpense")
@Validated
@Tag(name = "IncomeExpense", description = "Account's incomes and expenses")
public class IncomeExpenseController {
    @Autowired
    private IncomeExpenseService incomeExpenseService;
    @Autowired
    private AccountJPARepository accountJPARepository;
    @Operation(summary = "Account's income", description = "account's income")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request - Income not accepted"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "412", description = "Precondition failed"),
    })
    @PostMapping(value="/ingresar/{cid}/{aid}")
    public ResponseEntity<Income> anadirTarea (
            @Parameter(name = "cid", description = "User id", example = "1", required = true)
            @PathVariable @Min(1) Long cid,
            @Parameter(name = "aid", description = "Account to money id ", example = "1", required = true)
            @PathVariable @Min(1) Long aid,
            @Parameter(name = "income", description = "income", example = "1", required = true)
            @RequestBody @Valid Income income) {
        Account account = accountJPARepository.findById(aid).orElseThrow(
                ()-> new AccountException("Cuenta inexistente"));
        income.setMoneyTo(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(incomeExpenseService.addIncome(cid, income));
    }
}
