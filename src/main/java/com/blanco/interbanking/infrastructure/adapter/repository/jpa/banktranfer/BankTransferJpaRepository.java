package com.blanco.interbanking.infrastructure.adapter.repository.jpa.banktranfer;

import com.blanco.interbanking.infrastructure.adapter.repository.jpa.banktranfer.entity.BankTransferEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.common.LogicDeletableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransferJpaRepository extends LogicDeletableRepository<BankTransferEntity> {
}
