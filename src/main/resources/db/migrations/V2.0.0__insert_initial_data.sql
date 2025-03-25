-- Insertar en COMPANIES si no existen
INSERT INTO COMPANIES (id, cuit, company_name, created_at, adhesion_date, is_deleted)
SELECT 'C1', '20-12345678-9', 'Company A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE
WHERE NOT EXISTS (SELECT 1 FROM COMPANIES WHERE id = 'C1');

INSERT INTO COMPANIES (id, cuit, company_name, created_at, adhesion_date, is_deleted)
SELECT 'C2', '30-87654321-0', 'Company B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE
WHERE NOT EXISTS (SELECT 1 FROM COMPANIES WHERE id = 'C2');

-- Insertar en ACCOUNTS si no existen
INSERT INTO ACCOUNTS (id, company_id, account_number, created_at, is_deleted)
SELECT 'A1', 'C1', '1234567890', CURRENT_TIMESTAMP, FALSE
WHERE NOT EXISTS (SELECT 1 FROM ACCOUNTS WHERE id = 'A1');

INSERT INTO ACCOUNTS (id, company_id, account_number, created_at, is_deleted)
SELECT 'A2', 'C1', '0987654321', CURRENT_TIMESTAMP, FALSE
WHERE NOT EXISTS (SELECT 1 FROM ACCOUNTS WHERE id = 'A2');

INSERT INTO ACCOUNTS (id, company_id, account_number, created_at, is_deleted)
SELECT 'A3', 'C2', '5678901234', CURRENT_TIMESTAMP, FALSE
WHERE NOT EXISTS (SELECT 1 FROM ACCOUNTS WHERE id = 'A3');

-- Insertar en BANK_TRANSFERS si no existen
INSERT INTO BANK_TRANSFERS (id, amount, origin_id, target_id, created_at, is_deleted)
SELECT 'T1', 1500.00, 'A1', 'A2', CURRENT_TIMESTAMP - INTERVAL '10 day', FALSE
WHERE NOT EXISTS (SELECT 1 FROM BANK_TRANSFERS WHERE id = 'T1');

INSERT INTO BANK_TRANSFERS (id, amount, origin_id, target_id, created_at, is_deleted)
SELECT 'T2', 2000.50, 'A2', 'A3', CURRENT_TIMESTAMP - INTERVAL '50 day', FALSE
WHERE NOT EXISTS (SELECT 1 FROM BANK_TRANSFERS WHERE id = 'T2');

INSERT INTO BANK_TRANSFERS (id, amount, origin_id, target_id, created_at, is_deleted)
SELECT 'T3', 500.75, 'A3', 'A1', CURRENT_TIMESTAMP - INTERVAL '30 day', FALSE
WHERE NOT EXISTS (SELECT 1 FROM BANK_TRANSFERS WHERE id = 'T3');
