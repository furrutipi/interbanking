package com.blanco.interbanking.infrastructure.adapter.repository.jpa.common;

import com.blanco.interbanking.infrastructure.adapter.repository.jpa.common.entity.LogicDeletableEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LogicDeletableRepository<T extends LogicDeletableEntity> extends BaseRepository<T> {
}
