package com.blanco.interbanking.infrastructure.adapter.repository.jpa.banktranfer;

import com.blanco.interbanking.domain.model.BankTransfer;
import com.blanco.interbanking.domain.port.repository.BankTransferRepository;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.InfrastructureBusinessObjectFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BankTransferJpaAdapter implements BankTransferRepository {
    private final BankTransferJpaRepository bankTransferJpaRepository;
    private final InfrastructureBusinessObjectFactory infrastructureBusinessObjectFactory;

    public BankTransferJpaAdapter(BankTransferJpaRepository bankTransferJpaRepository, InfrastructureBusinessObjectFactory infrastructureBusinessObjectFactory) {
        this.bankTransferJpaRepository = bankTransferJpaRepository;
        this.infrastructureBusinessObjectFactory = infrastructureBusinessObjectFactory;
    }

    @Override
    public BankTransfer save(BankTransfer bankTransfer) {
        return infrastructureBusinessObjectFactory.createBankTransfer(bankTransferJpaRepository.save(infrastructureBusinessObjectFactory.createBankTransferEntity(bankTransfer)));
    }

    @Override
    public Optional<BankTransfer> findBankTransferById(String id) {
        return bankTransferJpaRepository.findById(id)
                .map(infrastructureBusinessObjectFactory::createBankTransfer);
    }

}
