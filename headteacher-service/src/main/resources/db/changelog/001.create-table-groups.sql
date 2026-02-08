--liquibase formatted sql
--changeset Sergey:1

CREATE TABLE groups(
    id BIGSERIAL PRIMARY KEY,
    group_name VARCHAR(100) NOT NULL UNIQUE
);