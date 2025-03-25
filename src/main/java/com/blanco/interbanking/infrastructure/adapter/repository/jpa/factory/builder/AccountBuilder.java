package com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.builder;

import com.blanco.interbanking.domain.model.Account;
import com.blanco.interbanking.domain.model.BankTransfer;
import com.blanco.interbanking.domain.model.Company;

import java.util.List;

public class AccountBuilder {
    private String id;
    private String accountNumber;
    private List<BankTransfer> sender;
    private List<BankTransfer> receiver;
    private Company company;

    public AccountBuilder id(String id) {
        this.id = id;
        return this;
    }

    public AccountBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder sender(List<BankTransfer> sender) {
        this.sender = sender;
        return this;
    }

    public AccountBuilder receiver(List<BankTransfer> receiver) {
        this.receiver = receiver;
        return this;
    }

    public AccountBuilder company(Company company) {
        this.company = company;
        return this;
    }

    public Account build() {
        Account account = new Account();
        account.setId(this.id);
        account.setAccountNumber(this.accountNumber);
        account.setSender(this.sender);
        account.setReceiver(this.receiver);
        account.setCompany(this.company);
        return account;
    }
}
