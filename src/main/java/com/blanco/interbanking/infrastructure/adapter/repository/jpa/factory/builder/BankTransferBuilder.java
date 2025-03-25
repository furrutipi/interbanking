package com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.builder;

import java.math.BigDecimal;
import com.blanco.interbanking.domain.model.Account;
import com.blanco.interbanking.domain.model.BankTransfer;

public class BankTransferBuilder {
    private String id;
    private BigDecimal amount;
    private Account originAccount;
    private Account targetAccount;
    private String originAccountId;
    private String targetAccountId;

    public BankTransferBuilder id(String id) {
        this.id = id;
        return this;
    }

    public BankTransferBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BankTransferBuilder originAccount(Account originAccount) {
        this.originAccount = originAccount;
        return this;
    }

    public BankTransferBuilder targetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
        return this;
    }

    public BankTransferBuilder originAccountId(String originAccountId) {
        this.originAccountId = originAccountId;
        return this;
    }

    public BankTransferBuilder targetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
        return this;
    }

    public BankTransfer build() {
        BankTransfer bankTransfer = new BankTransfer();
        bankTransfer.setId(this.id);
        bankTransfer.setAmount(this.amount);
        bankTransfer.setOriginAccount(this.originAccount);
        bankTransfer.setTargetAccount(this.targetAccount);
        bankTransfer.setOriginAccountId(this.originAccountId);
        bankTransfer.setTargetAccountId(this.targetAccountId);
        return bankTransfer;
    }
}
