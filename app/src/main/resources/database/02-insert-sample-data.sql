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
VALUES ('Manager'),
       ('Sales');

INSERT INTO customer_type (name)
VALUES ('Regular'),
       ('Premium');

INSERT INTO employee (employee_number, first_name, last_name, address, branch_id, role, supervisor)
VALUES ('EMP001', 'John', 'Doe', 1, 1, 1, NULL),
       ('EMP002', 'Jane', 'Smith', 2, 2, 2, 1);


INSERT INTO customer (address, customer_number, contact_data, customer_type, employee_id)
VALUES (1, 'CUST001', 1, 1, 1),
       (2, 'CUST002', 2, 2, 2);

INSERT INTO payment_details (payment_date, amount, status, payment_type, late_fee, notes)
VALUES ('2023-01-01', 100.00, 'Processed', 'Credit Card', 5.00, 'Monthly payment'),
       ('2023-02-01', 150.00, 'Pending', 'Debit', 10.00, 'Late payment');

INSERT INTO product (product_number, balance, opening_date, account_number, customer_id)
VALUES ('PROD001', 500.00, '2023-01-01', 'ACC001', 1),
       ('PROD002', 1000.00, '2023-02-01', 'ACC002', 2);

INSERT INTO credit (id, launch_date, due_date, interest_rate, payment_details_id)
VALUES (1, '2023-01-01', '2024-01-01', 5.00, 1);

INSERT INTO leasing (id, launch_date, due_date, interest_rate, product_id, payment_details_id)
VALUES (2, '2023-02-01', '2025-02-01', 4.5000, 2, 2);

INSERT INTO saving_account (id, interest_rate, product_id)
VALUES (1, 3.5000, 1);

INSERT INTO activity (date, status, description, customer_id, employee_id)
VALUES ('2023-03-01 10:00:00', 'Scheduled', 'Meeting with client', 1, 1),
       ('2023-04-01 11:00:00', 'Completed', 'Call with client', 2, 2);

INSERT INTO meeting (id, address_id)
VALUES (1, 1);

INSERT INTO call (id, start_time, contact_data)
VALUES (2, '2023-04-01 11:00:00', 1);

INSERT INTO offer (id, product_id, validity_period)
VALUES (1, 1, '2023-12-31');


