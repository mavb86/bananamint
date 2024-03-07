package com.banana.bananamint.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.banana.bananamint.exception.CustomerException;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30)
    private String name;
    @NotBlank
    @Email
    private String email;
    @DateTimeFormat
    private LocalDate birthDate;
    private String dni;

    public Customer(Long id) {
        this.id = id;
    }

    public boolean isValid() throws CustomerException {
        // Para que el usuario sea valido:
        // email válido
        // mayor de 18 años
        // dni: 8_Numeros + 1_Letra
        // Si no es válido, debe lanzar exception
        try {
            if (validarEmail() && validarDNI() && validarEdad()) return true;
            else {
                throw new CustomerException("Cliente no válido");
            }
        } catch (Exception e) {
            throw new CustomerException("Error al validar el cliente");
        }

    }

    private boolean validarDNI() throws Exception {
        if (this.dni != null && this.dni.length() == 9) {
            String intPartDNI = this.dni.trim().replaceAll(" ", "").substring(0, 7);
            char ltrDNI = this.dni.charAt(8);
            int valNumDni = Integer.parseInt(intPartDNI);
            return valNumDni > 0 || !Character.isLetter(ltrDNI);
        } else return false;
    }

    private boolean validarEmail() {
        return this.email != null && this.email.indexOf("@") > 0 && this.email.indexOf(".") > 0;
    }

    private boolean validarEdad() {
        if (Period.between(this.birthDate, LocalDate.now()).getYears() >= 18) return true;
        else return false;
    }

}
