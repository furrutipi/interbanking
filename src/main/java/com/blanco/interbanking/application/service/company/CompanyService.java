package com.blanco.interbanking.application.service.company;


import com.blanco.interbanking.application.factory.ApplicationBusinessObjectFactory;
import com.blanco.interbanking.domain.usecase.CompaniesRegisteredLastMonthUseCase;
import com.blanco.interbanking.domain.usecase.CompaniesWithTransfersLastMonthUseCase;
import com.blanco.interbanking.domain.usecase.RegisterCompanyUseCase;
import com.blanco.interbanking.infrastructure.controller.company.dto.CompanyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompaniesRegisteredLastMonthUseCase companiesRegisteredLastMonthUseCase;
    private final CompaniesWithTransfersLastMonthUseCase companiesWithTransfersLastMonthUseCase;
    private final RegisterCompanyUseCase registerCompanyUseCase;
    private final ApplicationBusinessObjectFactory applicationBusinessObjectFactory;

    public CompanyService(CompaniesRegisteredLastMonthUseCase companiesRegisteredLastMonthUseCase, CompaniesWithTransfersLastMonthUseCase companiesWithTransfersLastMonthUseCase, RegisterCompanyUseCase registerCompanyUseCase, ApplicationBusinessObjectFactory applicationBusinessObjectFactory) {
        this.companiesRegisteredLastMonthUseCase = companiesRegisteredLastMonthUseCase;
        this.companiesWithTransfersLastMonthUseCase = companiesWithTransfersLastMonthUseCase;
        this.registerCompanyUseCase = registerCompanyUseCase;
        this.applicationBusinessObjectFactory = applicationBusinessObjectFactory;
    }

    public List<CompanyDTO> companiesRegisteredLastMonth(){
       return companiesRegisteredLastMonthUseCase.execute().stream()
                .map(applicationBusinessObjectFactory::createCompanyDTO).toList();
    }

    public List<CompanyDTO> companiesWithTransfersLastMonth(){
       return companiesWithTransfersLastMonthUseCase.execute().stream()
               .map(applicationBusinessObjectFactory::createCompanyDTO).toList();
    }

    public CompanyDTO registerCompany(CompanyDTO newCompanyDTO){
        return applicationBusinessObjectFactory.createCompanyDTO(registerCompanyUseCase.execute(applicationBusinessObjectFactory.createCompany(newCompanyDTO)));
    }

}
