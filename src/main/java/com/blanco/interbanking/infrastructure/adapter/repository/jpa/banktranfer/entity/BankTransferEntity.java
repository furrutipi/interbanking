package com.blanco.interbanking.infrastructure.adapter.repository.jpa.banktranfer.entity;

import com.blanco.interbanking.infrastructure.adapter.repository.jpa.account.entity.AccountEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.common.entity.LogicDeletableEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "BANK_TRANSFERS")
public class BankTransferEntity extends LogicDeletableEntity {
    @Column(name = "amount")
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "origin_id")
    private AccountEntity origin;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_id")
    private AccountEntity target;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccountEntity getOrigin() {
        return origin;
    }

    public void setOrigin(AccountEntity origin) {
        this.origin = origin;
    }

    public AccountEntity getTarget() {
        return target;
    }

    public void setTarget(AccountEntity target) {
        this.target = target;
    }
}
