package com.banana.bananamint.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Goal ID", example = "1", required = true)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 30)
    @Schema(name = "Goal name", example = "Objetivo 1", required = true)
    private String name;

    @Size(min = 10, max = 255)
    @Schema(name = "Goal description", example = "Descripcion del objetivo", required = false)
    private String description;

    @NotNull
    @Min(0)
    @Schema(name = "Goal target amount", example = "5000.00", required = true)
    private double targetAmount;

    @Size(min = 2, max = 20)
    @NotBlank
    @Schema(name = "Goal status", example = "En proceso", required = true)
    private String status;

    @NotNull
    @DateTimeFormat
    @Schema(name = "Goal target date", example = "2025-01-01", required = true)
    private LocalDate targetDate;

    @JsonIgnore
    @Schema(name = "Goal user", example = "Usuario 1", required = true)
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer user;
}
