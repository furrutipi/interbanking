package com.blanco.interbanking.infrastructure.adapter.repository.jpa.company.entity;

import com.blanco.interbanking.infrastructure.adapter.repository.jpa.account.entity.AccountEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.common.entity.LogicDeletableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "COMPANIES")
public class CompanyEntity extends LogicDeletableEntity {
    @Column(name = "company_name")
    private String companyName;
    @OneToMany(mappedBy = "company", targetEntity = AccountEntity.class, orphanRemoval = true)
    private List<AccountEntity> accounts;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "adhesion_date")
    private LocalDateTime adhesionDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public LocalDateTime getAdhesionDate() {
        return adhesionDate;
    }

    public void setAdhesionDate(LocalDateTime adhesionDate) {
        this.adhesionDate = adhesionDate;
    }
}
