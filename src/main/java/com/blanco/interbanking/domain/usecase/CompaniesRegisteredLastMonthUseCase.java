package com.blanco.interbanking.domain.usecase;

import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.domain.port.repository.CompanyRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompaniesRegisteredLastMonthUseCase {
    private final CompanyRepository companyRepository;

    public CompaniesRegisteredLastMonthUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> execute(){
      return companyRepository.findByRegisteredDateBetweenLastMonth();
    }
}
