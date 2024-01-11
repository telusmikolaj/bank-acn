--liquibase formatted sql
--changeset liquibase:2

CREATE TABLE IF NOT EXISTS customer
(
    id SERIAL PRIMARY KEY,
    cif TEXT,
    first_name TEXT,
    last_name TEXT
);