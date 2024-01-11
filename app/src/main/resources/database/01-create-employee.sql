--liquibase formatted sql
--changeset liquibase:1

CREATE TABLE IF NOT EXISTS employee
(
    id SERIAL PRIMARY KEY,
    employee_number TEXT,
    first_name TEXT,
    last_name TEXT
);