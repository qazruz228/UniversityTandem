--liquibase formatted sql
--changeset Sergey:5

CREATE TYPE weekday AS ENUM(
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY'
);