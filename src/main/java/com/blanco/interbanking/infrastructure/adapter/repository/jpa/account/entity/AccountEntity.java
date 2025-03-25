package com.blanco.interbanking.infrastructure.adapter.repository.jpa.account.entity;

import com.blanco.interbanking.infrastructure.adapter.repository.jpa.banktranfer.entity.BankTransferEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.common.entity.LogicDeletableEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.company.entity.CompanyEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ACCOUNTS")
public class AccountEntity extends LogicDeletableEntity {

    @ManyToOne(targetEntity = CompanyEntity.class)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
    private String accountNumber;
    @OneToMany(mappedBy = "origin", targetEntity = BankTransferEntity.class)
    private List<BankTransferEntity> sender;
    @OneToMany(mappedBy = "target",targetEntity = BankTransferEntity.class)
    private List<BankTransferEntity> receiver;

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<BankTransferEntity> getSender() {
        return sender;
    }

    public void setSender(List<BankTransferEntity> sender) {
        this.sender = sender;
    }

    public List<BankTransferEntity> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<BankTransferEntity> receiver) {
        this.receiver = receiver;
    }
}
