CREATE TABLE IF NOT EXISTS address (
                                       id SERIAL PRIMARY KEY,
                                       street TEXT NOT NULL,
                                       city TEXT NOT NULL,
                                       province TEXT,
                                       postal_code TEXT NOT NULL,
                                       country TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS contact_data (
                                            id SERIAL PRIMARY KEY,
                                            phone TEXT UNIQUE,
                                            email TEXT UNIQUE
);
CREATE TABLE IF NOT EXISTS branch (
                                      id SERIAL PRIMARY KEY,
                                      address INT UNIQUE REFERENCES address(id)
);
CREATE TABLE IF NOT EXISTS role (
                                    id SERIAL PRIMARY KEY,
                                    name TEXT UNIQUE
);
CREATE TABLE IF NOT EXISTS customer_type (
                                             id SERIAL PRIMARY KEY,
                                             name TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS employee (
                                        id SERIAL PRIMARY KEY,
                                        employee_number TEXT UNIQUE,
                                        first_name TEXT NOT NULL,
                                        last_name TEXT NOT NULL,
                                        address INT REFERENCES address(id),
                                        branch_id INT REFERENCES branch(id),
                                        role INT NOT NULL REFERENCES role(id),
                                        supervisor INT REFERENCES employee(id)
);
CREATE TABLE IF NOT EXISTS customer (
                                        id SERIAL PRIMARY KEY,
                                        address INT REFERENCES address(id),
                                        customer_number TEXT UNIQUE,
                                        contact_data INT UNIQUE REFERENCES contact_data(id),
                                        customer_type INT REFERENCES customer_type(id),
                                        employee_id INT REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS payment_details (
                                               id SERIAL PRIMARY KEY,
                                               payment_date DATE NOT NULL,
                                               amount DECIMAL(10,2) NOT NULL,
                                               status TEXT,
                                               payment_type TEXT,
                                               late_fee DECIMAL(10, 2),
                                               notes TEXT
);


CREATE TABLE IF NOT EXISTS product (
                                       id SERIAL PRIMARY KEY,
                                       product_number TEXT,
                                       balance DECIMAL(10,2) NOT NULL CHECK (balance > 0),
                                       opening_date DATE NOT NULL,
                                       account_number TEXT UNIQUE,
                                       customer_id INT NOT NULL REFERENCES customer(id)
);


CREATE TABLE IF NOT EXISTS credit (
                                      id INT PRIMARY KEY,
                                      launch_date DATE NOT NULL,
                                      due_date DATE NOT NULL,
                                      interest_rate DECIMAL(5, 2) NOT NULL,
                                      payment_details_id INT REFERENCES payment_details(id),
                                      FOREIGN KEY (id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS leasing (
                                       id INT PRIMARY KEY,
                                       launch_date DATE NOT NULL,
                                       due_date DATE NOT NULL,
                                       interest_rate DECIMAL(5, 4) NOT NULL,
                                       product_id INT NOT NULL REFERENCES product(id),
                                       payment_details_id INT REFERENCES payment_details(id),
                                       FOREIGN KEY (id) REFERENCES product(id)

);

CREATE TABLE IF NOT EXISTS saving_account (
                                              id INT PRIMARY KEY,
                                              interest_rate DECIMAL(5, 4) NOT NULL,
                                              product_id INT NOT NULL REFERENCES product(id),
                                              FOREIGN KEY (id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS activity (
                                        id SERIAL PRIMARY KEY,
                                        date TIMESTAMP WITHOUT TIME ZONE NOT NULL ,
                                        status TEXT NOT NULL ,
                                        description TEXT,
                                        customer_id INT NOT NULL REFERENCES customer(id),
                                        employee_id INT NOT NULL REFERENCES employee(id)
);

CREATE TABLE IF NOT EXISTS meeting (
                                       id INT PRIMARY KEY,
                                       address_id INT NOT NULL REFERENCES address(id),
                                       FOREIGN KEY (id) REFERENCES activity(id)
);

CREATE TABLE IF NOT EXISTS call (
                                    id INT PRIMARY KEY,
                                    start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                                    contact_data INT NOT NULL REFERENCES contact_data(id),
                                    FOREIGN KEY (id) REFERENCES activity(id)
);

CREATE TABLE IF NOT EXISTS offer (
                                     id INT PRIMARY KEY,
                                     product_id INT NOT NULL REFERENCES product(id),
                                     validity_period DATE NOT NULL,
                                     FOREIGN KEY (id) REFERENCES activity(id)
);