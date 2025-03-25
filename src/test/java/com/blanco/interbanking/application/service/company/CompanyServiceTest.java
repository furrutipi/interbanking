package com.blanco.interbanking.application.service.company;

import com.blanco.interbanking.application.factory.ApplicationBusinessObjectFactory;
import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.domain.usecase.CompaniesRegisteredLastMonthUseCase;
import com.blanco.interbanking.domain.usecase.CompaniesWithTransfersLastMonthUseCase;
import com.blanco.interbanking.domain.usecase.RegisterCompanyUseCase;
import com.blanco.interbanking.infrastructure.controller.company.dto.CompanyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompaniesRegisteredLastMonthUseCase companiesRegisteredLastMonthUseCase;

    @Mock
    private CompaniesWithTransfersLastMonthUseCase companiesWithTransfersLastMonthUseCase;

    @Mock
    private RegisterCompanyUseCase registerCompanyUseCase;

    @Mock
    private ApplicationBusinessObjectFactory applicationBusinessObjectFactory;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void shouldReturnCompaniesRegisteredLastMonth() {
        // Arrange
        var companies = List.of(
                new Company("20-12345678-9", "Empresa A", LocalDateTime.now()),
                new Company("20-87654321-0", "Empresa B", LocalDateTime.now())
        );

        when(companiesRegisteredLastMonthUseCase.execute()).thenReturn(companies);
        when(applicationBusinessObjectFactory.createCompanyDTO(any(Company.class)))
                .thenAnswer(invocation -> {
                    Company company = invocation.getArgument(0);
                    return new CompanyDTO(company.getId(), company.getCompanyName(), company.getCuit(), company.getAdhesionDate());
                });

        // Act
        List<CompanyDTO> result = companyService.companiesRegisteredLastMonth();

        // Assert
        assertEquals(2, result.size());
        verify(companiesRegisteredLastMonthUseCase).execute();
        verify(applicationBusinessObjectFactory, times(2)).createCompanyDTO(any(Company.class));
    }

    @Test
    void shouldReturnCompaniesWithTransfersLastMonth() {
        // Arrange
        List<Company> companies = List.of(
                new Company("30-11111111-1", "Empresa X", LocalDateTime.now())
        );

        List<CompanyDTO> companyDTOs = List.of(
                new CompanyDTO("3", "Empresa X", "30-11111111-1", LocalDateTime.now())
        );

        when(companiesWithTransfersLastMonthUseCase.execute()).thenReturn(companies);
        when(applicationBusinessObjectFactory.createCompanyDTO(any(Company.class)))
                .thenAnswer(invocation -> {
                    Company company = invocation.getArgument(0);
                    return new CompanyDTO(company.getId(), company.getCompanyName(), company.getCuit(), company.getAdhesionDate());
                });

        // Act
        List<CompanyDTO> result = companyService.companiesWithTransfersLastMonth();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Empresa X", result.get(0).companyName());
        verify(companiesWithTransfersLastMonthUseCase).execute();
        verify(applicationBusinessObjectFactory).createCompanyDTO(any(Company.class));
    }

    @Test
    void shouldRegisterCompanySuccessfully() {
        // Arrange
        CompanyDTO newCompanyDTO = new CompanyDTO(null, "Empresa Nueva", "20-22222222-2", LocalDateTime.now());
        Company companyModel = new Company("20-22222222-2", "Empresa Nueva", LocalDateTime.now());
        Company savedCompany = new Company( "20-22222222-2", "Empresa Nueva", LocalDateTime.now());
        CompanyDTO savedCompanyDTO = new CompanyDTO("4", "Empresa Nueva", "20-22222222-2", LocalDateTime.now());

        when(applicationBusinessObjectFactory.createCompany(newCompanyDTO)).thenReturn(companyModel);
        when(registerCompanyUseCase.execute(companyModel)).thenReturn(savedCompany);
        when(applicationBusinessObjectFactory.createCompanyDTO(savedCompany)).thenReturn(savedCompanyDTO);

        // Act
        CompanyDTO result = companyService.registerCompany(newCompanyDTO);

        // Assert
        assertNotNull(result);
        assertEquals("4", result.id());
        assertEquals("Empresa Nueva", result.companyName());
        verify(applicationBusinessObjectFactory).createCompany(newCompanyDTO);
        verify(registerCompanyUseCase).execute(companyModel);
        verify(applicationBusinessObjectFactory).createCompanyDTO(savedCompany);
    }
}
