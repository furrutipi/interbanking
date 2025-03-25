INSERT INTO COMPANIES (id, cuit, company_name, created_at, adhesion_date, is_deleted)
VALUES
('C1', '20-12345678-9', 'Company A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE),
('C2', '30-87654321-0', 'Company B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE);

INSERT INTO ACCOUNTS (id, company_id, account_number, created_at, is_deleted)
VALUES
('A1', 'C1', '1234567890', CURRENT_TIMESTAMP, FALSE),
('A2', 'C1', '0987654321', CURRENT_TIMESTAMP, FALSE),
('A3', 'C2', '5678901234', CURRENT_TIMESTAMP, FALSE);

INSERT INTO BANK_TRANSFERS (id, amount, origin_id, target_id, created_at, is_deleted)
VALUES
('T1', 1500.00, 'A1', 'A2', CURRENT_TIMESTAMP, FALSE),
('T2', 2000.50, 'A2', 'A3', CURRENT_TIMESTAMP, FALSE),
('T3', 500.75, 'A3', 'A1', CURRENT_TIMESTAMP, FALSE);