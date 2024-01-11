CREATE TABLE address (
                         id SERIAL PRIMARY KEY,
                         street TEXT NOT NULL,
                         city TEXT NOT NULL,
                         province TEXT,
                         postal_code TEXT NOT NULL,
                         country TEXT NOT NULL
);

CREATE TABLE contact_data (
                              id SERIAL PRIMARY KEY,
                              phone TEXT UNIQUE,
                              email TEXT UNIQUE
);
CREATE TABLE branch (
                        id SERIAL PRIMARY KEY,
                        address INT UNIQUE REFERENCES address(id)
);
CREATE TABLE role (
                      id SERIAL PRIMARY KEY,
                      name TEXT UNIQUE
);
CREATE TABLE customer_type (
                               id SERIAL PRIMARY KEY,
                               name TEXT UNIQUE
);

CREATE TABLE employee (
                          id SERIAL PRIMARY KEY,
                          employee_number TEXT UNIQUE,
                          first_name TEXT NOT NULL,
                          last_name TEXT NOT NULL,
                          address INT REFERENCES address(id),
                          branch_id INT REFERENCES branch(id),
                          role INT NOT NULL REFERENCES role(id),
                          supervisor INT REFERENCES employee(id)
);
CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          address INT REFERENCES address(id),
                          contact_data INT UNIQUE REFERENCES contact_data(id),
                          customer_type INT REFERENCES customer_type(id),
                          employee_id INT REFERENCES employee(id)
);
CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         balance MONEY NOT NULL CHECK (balance >= 0),
                         opening_date DATE NOT NULL,
                         account_number TEXT UNIQUE,
                         customer_id INT NOT NULL REFERENCES customer(id)
);

CREATE TABLE payment_details (
                                 id SERIAL PRIMARY KEY,
                                 payment_date DATE NOT NULL,
                                 amount money NOT NULL,
                                 status TEXT,
                                 payment_type TEXT,
                                 late_fee DECIMAL(10, 2),
                                 notes TEXT
);
CREATE TABLE credit (
                        id INT PRIMARY KEY,
                        launch_date DATE NOT NULL,
                        due_date DATE NOT NULL,
                        interest_rate DECIMAL(5, 2) NOT NULL,
                        product_id INT REFERENCES product(id),
                        payment_details_id INT REFERENCES payment_details(id)
);

CREATE TABLE leasing (
                         id INT PRIMARY KEY,
                         launch_date DATE NOT NULL,
                         due_date DATE NOT NULL,
                         interest_rate DECIMAL(5, 4) NOT NULL,
                         product_id INT NOT NULL REFERENCES product(id),
                         payment_details_id INT REFERENCES payment_details(id)
);

CREATE TABLE saving_account (
                         id INT PRIMARY KEY,
                         interest_rate DECIMAL(5, 4) NOT NULL,
                         product_id INT NOT NULL REFERENCES product(id)
);

CREATE TABLE activity (
                          id SERIAL PRIMARY KEY,
                          date TIMESTAMP WITHOUT TIME ZONE NOT NULL ,
                          status TEXT NOT NULL ,
                          description TEXT,
                          customer_id INT NOT NULL REFERENCES customer(id),
                          employee_number INT NOT NULL REFERENCES employee(id)
);

CREATE TABLE meeting (
                         id INT PRIMARY KEY,
                         address_id INT NOT NULL REFERENCES address(id),
                         activity_id INT REFERENCES activity(id)
);

CREATE TABLE call (
                      id INT PRIMARY KEY,
                      start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                      contact_data INT NOT NULL REFERENCES contact_data(id),
                      activity_id INT NOT NULL REFERENCES activity(id)
);

CREATE TABLE offer (
                       id INT PRIMARY KEY,
                       activity_id INT NOT NULL REFERENCES activity(id),
                       product_id INT NOT NULL REFERENCES product(id),
                       validity_period DATE NOT NULL
);