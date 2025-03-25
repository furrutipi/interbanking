package com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory;



import com.blanco.interbanking.domain.model.Account;
import com.blanco.interbanking.domain.model.BankTransfer;
import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.account.entity.AccountEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.banktranfer.entity.BankTransferEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.company.entity.CompanyEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.builder.AccountBuilder;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.builder.BankTransferBuilder;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.builder.CompanyBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InfrastructureBusinessObjectFactory {
    public Company createCompany(CompanyEntity entity) {
        return new CompanyBuilder()
                .companyName(entity.getCompanyName())
                .cuit(entity.getCuit())
                .adhesionDate(entity.getAdhesionDate())
                .id(entity.getId())
                .build();
    }

    public CompanyEntity createCompanyEntity(Company model){
        var companyEntity =  new CompanyEntity();
        companyEntity.setCompanyName(model.getCompanyName());
        companyEntity.setCuit(model.getCuit());
        companyEntity.setAdhesionDate(model.getAdhesionDate());
        return companyEntity;
    }

    public BankTransfer createBankTransfer(BankTransferEntity entity){
        return new BankTransferBuilder().id(entity.getId())
                .amount(entity.getAmount())
                .originAccountId(entity.getOrigin().getId())
                .targetAccountId(entity.getTarget().getId())
                .originAccount(new AccountBuilder()
                        .accountNumber(entity.getOrigin().getAccountNumber())
                        .build())
                .targetAccount(new AccountBuilder().accountNumber(entity.getTarget().getAccountNumber())
                        .build()).build();
    }

    public BankTransferEntity createBankTransferEntity(BankTransfer model){
        var bankTransferEntity = new BankTransferEntity();
        bankTransferEntity.setAmount(model.getAmount());
        bankTransferEntity.setOrigin(null);

        return bankTransferEntity;
    }

    public AccountEntity createAccountEntity (Account model){
        var accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(model.getAccountNumber());
        accountEntity.setCompany(createCompanyEntity(model.getCompany()));
        if(Objects.nonNull(model.getReceiver()))
           accountEntity.setReceiver(model.getReceiver().stream().map(this::createBankTransferEntity).toList());
        if(Objects.nonNull(model.getSender()))
            accountEntity.setSender(model.getSender().stream().map(this::createBankTransferEntity).toList());

        return accountEntity;
    }

    public Account createAccount (AccountEntity entity){
        return new AccountBuilder()
                .company(createCompany(entity.getCompany()))
                .accountNumber(entity.getAccountNumber())
                .id(entity.getId())
                .sender(entity.getSender().stream().map(this::createBankTransfer).toList())
                .receiver(entity.getReceiver().stream().map(this::createBankTransfer).toList())
                .build();
    }

}
