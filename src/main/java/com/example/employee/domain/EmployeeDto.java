package com.example.employee.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EmployeeDto(@NotBlank String name, @NotNull BigDecimal salary, @NotBlank String cpf, @NotBlank String email) {
}
