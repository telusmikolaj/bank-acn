INSERT INTO address (street, city, province, postal_code, country)
VALUES ('123 Main St', 'Springfield', 'State', '12345', 'Country'),
       ('456 Elm St', 'Shelbyville', 'State', '67890', 'Country');

INSERT INTO contact_data (phone, email)
VALUES ('+1234567890', 'john.doe@example.com'),
       ('+1987654321', 'jane.doe@example.com');

INSERT INTO branch (address)
VALUES (1),
       (2);

INSERT INTO role (name)
VALUES ('MANAGER'),
       ('SALES');

INSERT INTO customer_type (name)
VALUES ('REGULAR'),
       ('PREMIUM');

INSERT INTO employee (employee_number, first_name, last_name, address, branch_id, role, supervisor)
VALUES ('EMP001', 'John', 'Doe', 1, 1, 1, NULL),
       ('EMP002', 'Jane', 'Smith', 2, 2, 2, 1);


INSERT INTO customer (address, customer_number, contact_data, customer_type, employee_id, cif)
VALUES (1, 'CUST001', 1, 1, 1, '12345678910'),
       (2, 'CUST002', 2, 2, 2, '10987654321');

INSERT INTO payment_details (payment_date, amount, status, payment_type, late_fee, notes)
VALUES ('2023-01-01', 100.00, 'Processed', 'Credit Card', 5.00, 'Monthly payment'),
       ('2023-02-01', 150.00, 'Pending', 'Debit', 10.00, 'Late payment');

INSERT INTO product (product_number, balance, opening_date, account_number, customer_id, type)
VALUES ('PROD001', 500.00, '2023-01-01', 'ACC001', 1, 'CREDIT'),
       ('PROD002', 1000.00, '2023-02-01', 'ACC002', 2, 'LEASING'),
       ('PROD003', 1000.00, '2023-01-01', 'ACC003', 1, 'CREDIT'),
       ('PROD004', 1000.00, '2023-02-01', 'ACC004', 2, 'LEASING'),
       ('PROD005', 1000.00, '2023-01-01', 'ACC005', 1, 'LEASING'),
       ('PROD006', 50000.00, '2023-02-01', 'ACC006', 2, 'CREDIT'),
       ('PROD007', 2000.00, '2023-01-01', 'ACC007', 1, 'LEASING'),
       ('PROD008', 50000.00, '2023-02-01', 'ACC008', 2, 'SAVING_ACCOUNT'),
       ('PROD009', 2000.00, '2023-01-01', 'ACC009', 1, 'SAVING_ACCOUNT');

INSERT INTO credit (id, launch_date, due_date, interest_rate, payment_details_id)
VALUES (1, '2023-01-01', '2024-01-01', 5.00, 1),
       (3, '2023-01-01', '2024-01-01', 100, 1),
       ( 6, '2023-01-01', '2024-01-01', 100, 1);


INSERT INTO leasing (id, launch_date, due_date, interest_rate, product_id, payment_details_id)
VALUES (2, '2023-02-01', '2025-02-01', 4.5000, 2, 2),
       (4, '2023-02-01', '2025-02-01', 4.5000, 2, 2),
       (5, '2023-02-01', '2025-02-01', 4.5000, 2, 2),
       (7, '2023-02-01', '2025-02-01', 4.5000, 2, 2);

INSERT INTO saving_account (id, interest_rate, product_id)
VALUES (8, 3.5000, 1),
       (9, 3.5000, 1);

INSERT INTO activity (date, status, description, customer_id, employee_id)
VALUES ('2023-03-01 10:00:00', 'Scheduled', 'Meeting with client', 1, 1),
       ('2023-04-01 11:00:00', 'Completed', 'Call with client', 2, 2);

INSERT INTO meeting (id, address_id)
VALUES (1, 1);

INSERT INTO call (id, start_time, contact_data)
VALUES (2, '2023-04-01 11:00:00', 1);

INSERT INTO offer (id, product_id, validity_period)
VALUES (1, 1, '2023-12-31');


