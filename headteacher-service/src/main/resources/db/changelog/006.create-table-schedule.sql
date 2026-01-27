--liquibase formatted sql
--changeset Sergey:6

CREATE TABLE scheduler
(
    id           BIGSERIAL PRIMARY KEY,

    group_id     BIGINT  NOT NULL,
    subject_id   BIGINT  NOT NULL,
    teacher_id   BIGINT  NOT NULL,

    day_of_week  weekday NOT NULL,
    lesson_order INT     NOT NULL,

    CONSTRAINT fk_schedule_group
        FOREIGN KEY (group_id)
            REFERENCES groups (id),

    CONSTRAINT fk_schedule_subject
        FOREIGN KEY (subject_id)
            REFERENCES subjects (id),

    CONSTRAINT fk_schedule_teacher
        FOREIGN KEY (teacher_id)
            REFERENCES teachers (id)

);
