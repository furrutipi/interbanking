package com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.builder;

import com.blanco.interbanking.domain.model.Company;

import java.time.LocalDateTime;

public class CompanyBuilder {
    private String companyName;
    private String cuit;
    private LocalDateTime adhesionDate;
    private String id;

    public CompanyBuilder cuit(String cuit) {
        this.cuit = cuit;
        return this;
    }

    public CompanyBuilder companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public CompanyBuilder adhesionDate(LocalDateTime adhesionDate) {
        this.adhesionDate = adhesionDate;
        return this;
    }

    public CompanyBuilder id(String id) {
        this.id = id;
        return this;
    }

    public Company build() {
        return new Company(cuit ,companyName , adhesionDate,id);
    }
}

