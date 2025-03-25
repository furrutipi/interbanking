
CREATE TABLE IF NOT EXISTS COMPANIES (
    id                VARCHAR(255) NOT NULL,
    adhesion_date     TIMESTAMP(6),
    cuit              VARCHAR(255),
    company_name      VARCHAR(255),
    deleted_at        TIMESTAMP(6),
    is_deleted        BOOLEAN,
    created_at        TIMESTAMP(6) NOT NULL,
    created_by        VARCHAR(255),
    modified_by       VARCHAR(255),
    updated_at        TIMESTAMP(6),
    PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_companies_cuit ON COMPANIES(cuit);
CREATE INDEX IF NOT EXISTS idx_companies_company_name ON COMPANIES(company_name);


CREATE TABLE IF NOT EXISTS ACCOUNTS (
    id             VARCHAR(255) NOT NULL,
    company_id     VARCHAR(255),
    account_number VARCHAR(255),
    deleted_at     TIMESTAMP(6),
    is_deleted     BOOLEAN,
    created_at     TIMESTAMP(6) NOT NULL,
    created_by     VARCHAR(255),
    modified_by    VARCHAR(255),
    updated_at     TIMESTAMP(6),
    PRIMARY KEY (id),
    CONSTRAINT fk_accounts_company FOREIGN KEY (company_id) REFERENCES COMPANIES(id)
);

CREATE INDEX IF NOT EXISTS idx_accounts_company_id ON ACCOUNTS(company_id);
CREATE INDEX IF NOT EXISTS idx_accounts_account_number ON ACCOUNTS(account_number);

CREATE TABLE IF NOT EXISTS BANK_TRANSFERS (
    id          VARCHAR(255) NOT NULL,
    amount      NUMERIC(38, 2),
    origin_id   VARCHAR(255),
    target_id   VARCHAR(255),
    created_at  TIMESTAMP(6) NOT NULL,
    created_by  VARCHAR(255),
    modified_by VARCHAR(255),
    updated_at  TIMESTAMP(6),
    deleted_at  TIMESTAMP(6),
    is_deleted  BOOLEAN,
    PRIMARY KEY (id),
    CONSTRAINT fk_bank_transfers_origin FOREIGN KEY (origin_id) REFERENCES ACCOUNTS(id),
    CONSTRAINT fk_bank_transfers_target FOREIGN KEY (target_id) REFERENCES ACCOUNTS(id)
);

CREATE INDEX IF NOT EXISTS idx_bank_transfers_origin_id ON BANK_TRANSFERS(origin_id);
CREATE INDEX IF NOT EXISTS idx_bank_transfers_target_id ON BANK_TRANSFERS(target_id);


