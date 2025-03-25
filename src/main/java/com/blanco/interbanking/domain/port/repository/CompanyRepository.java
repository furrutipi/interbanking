package com.blanco.interbanking.domain.port.repository;


import com.blanco.interbanking.domain.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    Company save(Company company);
    List<Company> findCompaniesWithTransfersLastMonth();
    List<Company> findByRegisteredDateBetweenLastMonth();
    Optional<Company> findById(String id);
    Optional<Company> findByCuitOrName(String cuit, String name);
}
