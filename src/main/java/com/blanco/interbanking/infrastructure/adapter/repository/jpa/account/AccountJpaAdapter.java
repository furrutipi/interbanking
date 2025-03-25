package com.blanco.interbanking.infrastructure.adapter.repository.jpa.account;


import com.blanco.interbanking.domain.model.Account;
import com.blanco.interbanking.domain.port.repository.AccountRepository;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory.InfrastructureBusinessObjectFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountJpaAdapter implements AccountRepository {

    private final InfrastructureBusinessObjectFactory infrastructureBusinessObjectFactory;

    private final AccountJpaRepository accountJpaRepository;

    public AccountJpaAdapter(InfrastructureBusinessObjectFactory infrastructureBusinessObjectFactory, AccountJpaRepository accountJpaRepository) {
        this.infrastructureBusinessObjectFactory = infrastructureBusinessObjectFactory;
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Account save(Account account) {
        return infrastructureBusinessObjectFactory.createAccount(this.accountJpaRepository.save(infrastructureBusinessObjectFactory.createAccountEntity(account)));
    }

    @Override
    public Optional<Account> findAccountById(String id) {
        return accountJpaRepository.findById(id).map(infrastructureBusinessObjectFactory::createAccount);
    }
}
