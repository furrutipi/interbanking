package com.blanco.interbanking.infrastructure.controller.company;


import com.blanco.interbanking.application.service.company.CompanyService;

import com.blanco.interbanking.infrastructure.controller.company.dto.CompaniesDTO;
import com.blanco.interbanking.infrastructure.controller.company.dto.CompanyDTO;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import static com.blanco.interbanking.infrastructure.util.UrlEndpoint.*;


@RestController
@RequestMapping(BASE_PATH_V1 + COMPANY)
@Tag(name = "Company", description = "Endpoints for managing companies")
public class CompanyRestController {

    private final CompanyService companyService;

    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Operation(
            summary = "Register a new company",
            description = "Creates a new company and returns the registered company data",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Company successfully created",
                            content = @Content(schema = @Schema(implementation = CompanyDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping(REGISTER)
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.registerCompany(request));
    }

    @Operation(
            summary = "Get companies registered in the last month",
            description = "Retrieves a list of companies that were registered in the last month",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of companies",
                            content = @Content(schema = @Schema(implementation = CompaniesDTO.class))),
                    @ApiResponse(responseCode = "204", description = "No companies found in the last month"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping(ADHERED_LAST_MONTH)
    public ResponseEntity<CompaniesDTO> companiesRegisteredLastMonth() {
        return buildResponse(companyService.companiesRegisteredLastMonth());
    }

    @Operation(
            summary = "Get companies with transfers in the last month",
            description = "Retrieves a list of companies that performed transfers in the last month",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of companies",
                            content = @Content(schema = @Schema(implementation = CompaniesDTO.class))),
                    @ApiResponse(responseCode = "204", description = "No companies found with transfers in the last month"),
                    @ApiResponse(responseCode = "500")
            }
    )
    @GetMapping(TRANSFER_LAST_MONTH)
    public ResponseEntity<CompaniesDTO> findCompaniesWithTransfersLastMonth() {
        return buildResponse(companyService.companiesWithTransfersLastMonth());
    }

    private ResponseEntity<CompaniesDTO> buildResponse(List<CompanyDTO> companies) {
        return Optional.of(companies)
                .filter(list -> !list.isEmpty())
                .map(CompaniesDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
}
