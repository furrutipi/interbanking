package com.blanco.interbanking.domain.validator;

import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.domain.port.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Component
public class CompanyValidator {

    private final CompanyRepository companyRepository;

    public CompanyValidator(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void validateIfCompanyExist(Company newCompany) {
        companyRepository.findByCuitOrName(newCompany.getCuit(), newCompany.getCompanyName()).ifPresent(c -> {
            throw new IllegalStateException("Company already exists");
        });
    }

    public void validateAdhesionDate(LocalDateTime adhesionDate){
        if (adhesionDate.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("The date of accession cannot be in the future.");
    }

    public void isValidCuit(String input) {

        String regex = "^\\d{2}-\\d{8}-\\d{1}$";

        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(input);
        if(!matcher.matches())
            throw new IllegalArgumentException("Cuit format not valid.");
    }



}