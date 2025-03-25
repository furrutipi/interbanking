package com.blanco.interbanking.application.factory;

import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.infrastructure.controller.company.dto.CompanyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationBusinessObjectFactoryTest {

    private ApplicationBusinessObjectFactory applicationBusinessObjectFactory;

    @BeforeEach
    void setUp() {
        applicationBusinessObjectFactory = new ApplicationBusinessObjectFactory();
    }

    @Test
    void shouldCreateCompanyFromDTO() {
        // Arrange
        var dto = new CompanyDTO("1", "Empresa X", "20-12345678-9", LocalDateTime.of(2024, 3, 1, 10, 0));

        // Act
        var company = applicationBusinessObjectFactory.createCompany(dto);

        // Assert
        assertNotNull(company);
        assertEquals("Empresa X", company.getCompanyName());
        assertEquals("20-12345678-9", company.getCuit());
        assertEquals(LocalDateTime.of(2024, 3, 1, 10, 0), company.getAdhesionDate());
    }

    @Test
    void shouldCreateCompanyDTOFromModel() {

        var company = new Company( "20-87654321-0", "Empresa Y", LocalDateTime.of(2024, 3, 5, 15, 30));

        var dto = applicationBusinessObjectFactory.createCompanyDTO(company);

        // Assert
        assertNotNull(dto);
        assertEquals("Empresa Y", dto.companyName());
        assertEquals("20-87654321-0", dto.cuit());
        assertEquals(LocalDateTime.of(2024, 3, 5, 15, 30), dto.adhesionDate());
    }
}
