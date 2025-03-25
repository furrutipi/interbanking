package com.blanco.interbanking.infrastructure.adapter.repository.jpa.account;

import com.blanco.interbanking.infrastructure.adapter.repository.jpa.account.entity.AccountEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.common.LogicDeletableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends LogicDeletableRepository<AccountEntity> {

}
