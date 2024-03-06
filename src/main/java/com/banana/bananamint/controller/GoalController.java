package com.banana.bananamint.controller;

import com.banana.bananamint.domain.Goal;
import com.banana.bananamint.exception.GoalException;
import com.banana.bananamint.persistence.CustomerJPARepository;
import com.banana.bananamint.services.GoalServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/goals")
@Validated
public class GoalController {
    @Autowired
    GoalServiceImpl service;
    @Autowired
    CustomerJPARepository customerRepo;

    @Operation(summary = "Muestra los objetivos de un cliente", description = "Devuelve la lista de objetivos del cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correctamente devuelto"),
            @ApiResponse(responseCode = "404", description = "No se han encontrado objetivos para el cliente")
    })
    @GetMapping(value = "/customer/{idCustomer}")
    public ResponseEntity<List<Goal>> getAllByCostumer(@PathVariable @Min(1) Long idCustomer) throws SQLException {
        List<Goal> listGoals = service.showAll(idCustomer);
        if (listGoals != null && listGoals.size() > 0) return new ResponseEntity<>(listGoals, HttpStatus.OK);
        else throw new GoalException("No se han encontrado objetivos para el cliente "+idCustomer);
    }

    @Operation(summary = "Añade un objetivo por cliente", description = "Persiste el nuevo objetivo y devuelve una lista de los objetivos del cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Objetivo correctamente creado"),
            @ApiResponse(responseCode = "4XX", description = "Solicitud incorrecta")
    })
    @PostMapping(value = "/customer/{idCustomer}")
    public ResponseEntity<List<Goal>> addByCostumer(@PathVariable @Min(1) Long idCustomer, @RequestBody @Valid Goal goal) throws SQLException {
        goal.setId(null);
        goal.setUser(customerRepo.findById(idCustomer).get());
        return new ResponseEntity<>(service.add(idCustomer, goal), HttpStatus.CREATED);
    }
}
