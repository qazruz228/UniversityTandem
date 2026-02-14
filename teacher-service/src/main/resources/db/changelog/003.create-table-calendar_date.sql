--liquibase formatted sql
--changeset Sergey:3

CREATE TABLE calendar_date(
    id         BIGSERIAL PRIMARY KEY,
    date       DATE    NOT NULL UNIQUE,
    is_working BOOLEAN NOT NULL
);