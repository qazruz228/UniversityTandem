--liquibase formatted sql
--changeset Sergey:4

CREATE TYPE subject_enum AS ENUM (
    'MATH',
    'PHYSICS',
    'CHEMISTRY',
    'BIOLOGY',
    'HISTORY',
    'LITERATURE'
    );


