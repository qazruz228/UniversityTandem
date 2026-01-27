--liquibase formatted sql
--changeset Sergey:2

CREATE TABLE subjects(

    id BIGSERIAL PRIMARY KEY,
    subject_name VARCHAR(100) NOT NULL UNIQUE
);