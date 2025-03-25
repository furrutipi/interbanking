package com.blanco.interbanking.domain.usecase;

import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.domain.port.repository.CompanyRepository;
import com.blanco.interbanking.domain.validator.CompanyValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterCompanyUseCaseTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyValidator companyValidator;

    @InjectMocks
    private RegisterCompanyUseCase useCase;

    @Test
    void shouldRegisterNewCompanySuccessfully() {

        var newCompany = new Company("20-12345678-9", "Empresa Nueva", LocalDateTime.now());

        when(companyRepository.save(any(Company.class))).thenReturn(newCompany);

        var result = useCase.execute(newCompany);

        assertNotNull(result);
        assertEquals("Empresa Nueva", result.getCompanyName());
        verify(companyValidator).validateIfCompanyExist(newCompany);
        verify(companyRepository).save(newCompany);
    }

    @Test
    void shouldThrowExceptionIfCompanyExists() {
        var existingCompany = new Company("20-12345678-9", "Empresa Existente", LocalDateTime.now());

        doThrow(new IllegalArgumentException("La empresa ya existe"))
                .when(companyValidator).validateIfCompanyExist(existingCompany);

        var exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(existingCompany));
        assertEquals("La empresa ya existe", exception.getMessage());

        verify(companyValidator).validateIfCompanyExist(existingCompany);
        verify(companyRepository, never()).save(any(Company.class));
    }
}
