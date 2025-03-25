package com.blanco.interbanking.infrastructure.adapter.repository.jpa.factory;

import static org.junit.jupiter.api.Assertions.*;

import com.blanco.interbanking.domain.model.Account;
import com.blanco.interbanking.domain.model.BankTransfer;
import com.blanco.interbanking.domain.model.Company;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.account.entity.AccountEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.banktranfer.entity.BankTransferEntity;
import com.blanco.interbanking.infrastructure.adapter.repository.jpa.company.entity.CompanyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class InfrastructureBusinessObjectFactoryTest {

    @InjectMocks
    private InfrastructureBusinessObjectFactory factory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCompany() {
        CompanyEntity entity = new CompanyEntity();
        entity.setCompanyName("Test Company");
        entity.setCuit("123456789");
        entity.setAdhesionDate(LocalDateTime.now());
        entity.setId("1");

        Company company = factory.createCompany(entity);

        assertNotNull(company);
        assertEquals("Test Company", company.getCompanyName());
        assertEquals("123456789", company.getCuit());
        assertEquals("1", company.getId());
    }

    @Test
    void testCreateCompanyEntity() {
        Company model = new Company();
        model.setCompanyName("Test Company");
        model.setCuit("123456789");
        model.setAdhesionDate(LocalDateTime.now());

        CompanyEntity entity = factory.createCompanyEntity(model);

        assertNotNull(entity);
        assertEquals("Test Company", entity.getCompanyName());
        assertEquals("123456789", entity.getCuit());
    }

    @Test
    void testCreateBankTransfer() {
        BankTransferEntity entity = new BankTransferEntity();
        entity.setId("2");
        entity.setAmount(BigDecimal.valueOf(1000));

        AccountEntity origin = new AccountEntity();
        origin.setId("O1");
        origin.setAccountNumber("12345");
        entity.setOrigin(origin);

        AccountEntity target = new AccountEntity();
        target.setId("T1");
        target.setAccountNumber("67890");
        entity.setTarget(target);

        BankTransfer bankTransfer = factory.createBankTransfer(entity);

        assertNotNull(bankTransfer);
        assertEquals("2", bankTransfer.getId());
        assertEquals(BigDecimal.valueOf(1000), bankTransfer.getAmount());
        assertEquals("12345", bankTransfer.getOriginAccount().getAccountNumber());
        assertEquals("67890", bankTransfer.getTargetAccount().getAccountNumber());
    }

    @Test
    void testCreateBankTransferEntity() {
        BankTransfer model = new BankTransfer();
        model.setAmount(BigDecimal.valueOf(2000));

        BankTransferEntity entity = factory.createBankTransferEntity(model);

        assertNotNull(entity);
        assertEquals(BigDecimal.valueOf(2000), entity.getAmount());
    }

    @Test
    void testCreateAccountEntity() {
        Account model = new Account();
        model.setAccountNumber("99999");
        Company company = new Company();
        company.setCompanyName("Company X");
        model.setCompany(company);

        AccountEntity entity = factory.createAccountEntity(model);

        assertNotNull(entity);
        assertEquals("99999", entity.getAccountNumber());
        assertEquals("Company X", entity.getCompany().getCompanyName());
    }
}
