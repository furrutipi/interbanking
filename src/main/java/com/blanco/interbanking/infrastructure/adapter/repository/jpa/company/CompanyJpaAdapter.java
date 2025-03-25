package com.blanco.interbanking.infrastructure.adapter.repository.jpa.company;

import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.domain.port.repository.CompanyRepository;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.InfrastructureBusinessObjectFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyJpaAdapter implements CompanyRepository {
    private final CompanyJpaRepository companyJpaRepository;
    private final InfrastructureBusinessObjectFactory infrastructureBusinessObjectFactory;

    public CompanyJpaAdapter(CompanyJpaRepository companyJpaRepository, InfrastructureBusinessObjectFactory infrastructureBusinessObjectFactory) {
        this.companyJpaRepository = companyJpaRepository;
        this.infrastructureBusinessObjectFactory = infrastructureBusinessObjectFactory;
    }

    @Override
    public Company save(Company company) {
        var entity = infrastructureBusinessObjectFactory.createCompanyEntity(company);
        var persistedEntity = companyJpaRepository.save(entity);
        return infrastructureBusinessObjectFactory.createCompany(persistedEntity);
    }

    @Override
    public List<Company> findCompaniesWithTransfersLastMonth() {
        return companyJpaRepository.findByTransfersDateBetweenLastMonth(LocalDateTime.now().minusMonths(1))
                .stream()
                .map(infrastructureBusinessObjectFactory::createCompany)
                .toList();
    }

    @Override
    public List<Company> findByRegisteredDateBetweenLastMonth() {
        return companyJpaRepository.findByAdhesionDateAfter(LocalDateTime.now().minusMonths(1))
                .stream()
                .map(infrastructureBusinessObjectFactory::createCompany)
                .toList();
    }

    @Override
    public Optional<Company> findById(String id) {
        var optionalCompany = companyJpaRepository.findById(id);
        return optionalCompany.map(infrastructureBusinessObjectFactory::createCompany);
    }

    @Override
    public Optional<Company> findByCuitOrName(String cuit, String name) {
        var optionalCompany = companyJpaRepository.findByCompanyNameOrCuit(cuit, name);
        return optionalCompany.map(infrastructureBusinessObjectFactory::createCompany);
    }
}
