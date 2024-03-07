INSERT INTO customer (name, email, birth_date, dni) VALUES
('Alex Perez','alex@bananamint.com','1985-03-06','12345678L'),
('Pepa Gomez','pepa@bananamint.com','1979-10-06','87654321T');

INSERT INTO account (type, opening_Date, balance, max_Overdraft, active, customer_id) VALUES
('Cuenta corriente','2020-03-05',2000, 500, true, 1);

INSERT INTO expense (customer_id, amount, due_Date, account_id, status) VALUES
(1, 500, '2024-01-02', 1, 'pagado'),
(1, 10, '2024-02-02', 1, 'pagado'),
(2, 50, '2024-02-03', 1, 'pagado'),
(1, 560, '2024-01-02', 1, 'pagado'),
(1, 501, '2024-02-02', 1, 'pagado');

INSERT INTO income (customer_id, amount, enter_Date, account_id, status) VALUES
(1, 1200, '2024-01-02', 1, 'ingresado'),
(1, 100, '2024-03-02', 1, 'ingresado'),
(1, 50, '2024-02-21', 1, 'ingresado'),
(1, 500, '2024-02-02', 1, 'ingresado'),
(2, 500, '2024-02-02', 1, 'ingresado'),
(1, 501, '2024-02-02', 1, 'ingresado');