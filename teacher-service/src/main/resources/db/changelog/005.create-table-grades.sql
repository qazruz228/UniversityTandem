--liquibase formatted sql
--changeset Sergey:1

CREATE TABLE grades
(
    id           BIGSERIAL PRIMARY KEY,
    grade        INTEGER     NOT NULL,
    student_id   BIGINT       NOT NULL,
    subject_name subject_enum NOT NULL,
    date_id      BIGINT       NOT NULL,
    group_id BIGINT NOT NULL,

    CONSTRAINT fk_grades_student
        FOREIGN KEY (student_id)
            REFERENCES students (id),

    CONSTRAINT fk_grades_date
        FOREIGN KEY (date_id)
            REFERENCES calendar_date (id),

    CONSTRAINT fk_grades_groups
        FOREIGN KEY (group_id)
            REFERENCES groups (id),

    CONSTRAINT chk_grade_range
        CHECK (grade BETWEEN 1 AND 5),

    CONSTRAINT uq_grade_per_day
        UNIQUE (student_id, subject_name, date_id)
);