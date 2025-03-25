package com.blanco.interbanking.infrastructure.adapter.repository.jpa.common;

import com.blanco.interbanking.infrastructure.adapter.repository.jpa.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity> extends JpaRepository<T, String> {
}
