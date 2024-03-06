package com.banana.bananamint.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(name = "income", description = "account's income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Income ID", example = "1", required = false)
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    @Schema(name = "Customer ID", example = "1", required = true)
    private Customer user;
    @Min(1)
    @Schema(name = "Amount", example = "1000", required = true)
    private double amount;
    @DateTimeFormat
    @Schema(name = "Income's date", example = "2024-03-05", required = true)
    private LocalDate enterDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    @Schema(name = "Account ID", example = "1", required = true)
    private Account moneyTo;
    @Schema(name = "Status", example = "accepted", required = true)
    private String status;

}
