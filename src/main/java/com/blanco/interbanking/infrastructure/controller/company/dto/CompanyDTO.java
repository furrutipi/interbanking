package com.blanco.interbanking.infrastructure.controller.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Schema(description = "DTO representing company data")
public record CompanyDTO(
        String id,
        @NotBlank
        String companyName,
        @NotBlank
        String cuit,
        LocalDateTime adhesionDate
) {}
