package com.blanco.interbanking.domain.port.repository;



import com.blanco.interbanking.domain.model.BankTransfer;

import java.util.Optional;

public interface BankTransferRepository {
    BankTransfer save(BankTransfer account);

    Optional<BankTransfer> findBankTransferById(String id);
}
