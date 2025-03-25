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
class CompaniesWithTransfersLastMonthUseCaseTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompaniesWithTransfersLastMonthUseCase useCase;

    @Test
    void shouldReturnCompaniesWithTransfersLastMonth() {

        var mockCompanies = List.of(
                new Company("1", "20-12345678-9", "Empresa 1", LocalDateTime.now().minusDays(10), LocalDateTime.now(), LocalDateTime.now(), false, null)
        );

        when(companyRepository.findCompaniesWithTransfersLastMonth()).thenReturn(mockCompanies);

        var result = useCase.execute();

        assertEquals(1, result.size());
        assertEquals("Empresa 1", result.getFirst().getCompanyName());
        verify(companyRepository).findCompaniesWithTransfersLastMonth();
    }
}