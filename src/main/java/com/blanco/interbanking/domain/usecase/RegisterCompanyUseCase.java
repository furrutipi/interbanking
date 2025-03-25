package com.blanco.interbanking.domain.usecase;

import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.domain.port.repository.CompanyRepository;
import com.blanco.interbanking.domain.validator.CompanyValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class RegisterCompanyUseCase {
    private final CompanyRepository companyRepository;
    private final CompanyValidator companyValidator;

    public RegisterCompanyUseCase(CompanyRepository companyRepository, CompanyValidator companyValidator) {
        this.companyRepository = companyRepository;
        this.companyValidator = companyValidator;
    }

    public Company execute(Company newCompany){
        validateIfCompanyAlreadyExist(newCompany);
        validateFields(newCompany);
        return companyRepository.save(newCompany);
    }

    private void validateIfCompanyAlreadyExist(Company company){
        companyValidator.validateIfCompanyExist(company);
    }
    private void validateFields(Company company){
        if(Objects.nonNull(company.getAdhesionDate()))
            companyValidator.validateAdhesionDate(company.getAdhesionDate());
        else
            company.setAdhesionDate(LocalDateTime.now());
        companyValidator.isValidCuit(company.getCuit());
    }
}
