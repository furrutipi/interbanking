package com.blanco.interbanking.domain.port.repository;



import com.blanco.interbanking.domain.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findAccountById(String id);
}
