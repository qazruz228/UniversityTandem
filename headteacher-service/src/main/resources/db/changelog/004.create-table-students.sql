--liquibase formatted sql
--changeset Sergey:4

CREATE TABLE students
(
    id              BIGSERIAL PRIMARY KEY,
    student_name    VARCHAR(100) NOT NULL,
    student_surname VARCHAR(100) NOT NULL,
    group_id        BIGINT       NOT NULL,

    CONSTRAINT fk_student_group
        FOREIGN KEY (group_id)
            REFERENCES groups (id)

);