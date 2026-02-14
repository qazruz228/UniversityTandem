--liquibase formatted sql
--changeset Sergey:2

CREATE TABLE students(
    id              BIGSERIAL PRIMARY KEY,
    student_name    VARCHAR(50)  NOT NULL,
    student_surname VARCHAR(50)  NOT NULL,
    email           VARCHAR(100) NOT NULL,
    group_id        BIGINT       NOT NULL,
    CONSTRAINT fk_students_group
        FOREIGN KEY (group_id)
            REFERENCES groups (id)
);
