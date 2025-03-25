package com.blanco.interbanking.infrastructure.adapter.repository.jpa.company;

import com.blanco.interbanking.infrastructure.adapter.repository.jpa.common.LogicDeletableRepository;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends LogicDeletableRepository<CompanyEntity> {

    Optional<CompanyEntity> findByCompanyNameOrCuit(String name, String cuit);

    List<CompanyEntity> findByAdhesionDateAfter(LocalDateTime time);

    @Query(value = """
    SELECT DISTINCT c.* FROM COMPANIES c
    JOIN ACCOUNTS a ON c.id = a.company_id
    LEFT JOIN BANK_TRANSFERS s ON a.id = s.origin_id
    LEFT JOIN BANK_TRANSFERS r ON a.id = r.target_id
    WHERE (s.created_at >= :monthAgo OR r.created_at >= :monthAgo)
    """, nativeQuery = true)
    List<CompanyEntity> findByTransfersDateBetweenLastMonth(@Param("monthAgo") LocalDateTime monthAgo);





}
