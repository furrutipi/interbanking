package com.blanco.interbanking.domain.usecase;

import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.domain.port.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompaniesRegisteredLastMonthUseCaseTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompaniesRegisteredLastMonthUseCase useCase;

    @Test
    void shouldReturnCompaniesRegisteredLastMonth() {

        var mockCompanies = List.of(
                new Company("1", "20-12345678-9", "Empresa 1", LocalDateTime.now().minusDays(20), LocalDateTime.now(), LocalDateTime.now(), false, null),
                new Company("2", "20-87654321-0", "Empresa 2", LocalDateTime.now().minusDays(25), LocalDateTime.now(), LocalDateTime.now(), false, null)
        );

        when(companyRepository.findByRegisteredDateBetweenLastMonth()).thenReturn(mockCompanies);

        var result = useCase.execute();

        assertEquals(2, result.size());
        verify(companyRepository).findByRegisteredDateBetweenLastMonth();
    }
}