--liquibase formatted sql
--changeset Sergey:3

CREATE TABLE teachers
(
    id              BIGSERIAL PRIMARY KEY,
    teacher_name    VARCHAR(100) NOT NULL,
    teacher_surname VARCHAR(100) NOT NULL,
    subject_id      BIGINT       NOT NULL,

    CONSTRAINT fk_teacher_subject
        FOREIGN KEY (subject_id)
            REFERENCES subjects (id)

);