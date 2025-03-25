DROP TABLE IF EXISTS COMPANIES;
DROP TABLE IF EXISTS ACCOUNTS;
DROP TABLE IF EXISTS BANK_TRANSFERS;

CREATE TABLE IF NOT EXISTS COMPANIES (
    id                VARCHAR(255) PRIMARY KEY,
    adhesion_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cuit              VARCHAR(255),
    company_name      VARCHAR(255),
    deleted_at        TIMESTAMP,
    is_deleted        BOOLEAN,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by        VARCHAR(255),
    modified_by       VARCHAR(255),
    updated_at        TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_companies_cuit ON COMPANIES(cuit);
CREATE INDEX IF NOT EXISTS idx_companies_company_name ON COMPANIES(company_name);

CREATE TABLE IF NOT EXISTS ACCOUNTS (
    id             VARCHAR(255) PRIMARY KEY,
    company_id     VARCHAR(255),
    account_number VARCHAR(255),
    deleted_at     TIMESTAMP,
    is_deleted     BOOLEAN,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by     VARCHAR(255),
    modified_by    VARCHAR(255),
    updated_at     TIMESTAMP,
    CONSTRAINT fk_accounts_company FOREIGN KEY (company_id) REFERENCES COMPANIES(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_accounts_company_id ON ACCOUNTS(company_id);
CREATE INDEX IF NOT EXISTS idx_accounts_account_number ON ACCOUNTS(account_number);

CREATE TABLE IF NOT EXISTS BANK_TRANSFERS (
    id          VARCHAR(255) PRIMARY KEY,
    amount      NUMERIC(38, 2),
    origin_id   VARCHAR(255),
    target_id   VARCHAR(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by  VARCHAR(255),
    modified_by VARCHAR(255),
    updated_at  TIMESTAMP,
    deleted_at  TIMESTAMP,
    is_deleted  BOOLEAN,
    CONSTRAINT fk_bank_transfers_origin FOREIGN KEY (origin_id) REFERENCES ACCOUNTS(id) ON DELETE CASCADE,
    CONSTRAINT fk_bank_transfers_target FOREIGN KEY (target_id) REFERENCES ACCOUNTS(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_bank_transfers_origin_id ON BANK_TRANSFERS(origin_id);
CREATE INDEX IF NOT EXISTS idx_bank_transfers_target_id ON BANK_TRANSFERS(target_id);





