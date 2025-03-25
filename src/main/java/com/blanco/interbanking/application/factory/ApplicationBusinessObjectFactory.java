package com.blanco.interbanking.application.factory;

import com.blanco.interbanking.application.factory.builder.CompanyBuilder;
import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.infrastructure.controller.company.dto.CompanyDTO;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBusinessObjectFactory {
    public Company createCompany(CompanyDTO dto) {
        return new CompanyBuilder()
                .companyName(dto.companyName())
                .cuit(dto.cuit())
                .adhesionDate(dto.adhesionDate())
                .build();
    }

    public CompanyDTO createCompanyDTO(Company model){
        return new CompanyDTO(model.getId(), model.getCompanyName(),model.getCuit(),model.getAdhesionDate());
    }
}

