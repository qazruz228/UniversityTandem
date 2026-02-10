-- 1. Заполняем таблицу groups
-- ==========================
INSERT INTO groups (group_name)
VALUES
    ('Group A'),
    ('Group B'),
    ('Group C'),
    ('Group D');

-- ==========================
-- 2. Заполняем таблицу students
-- ==========================
-- Пример генерации случайных студентов
INSERT INTO students (student_name, student_surname, email, group_id)
VALUES
    ('Ivan', 'Ivanov', 'ivan.ivanov@example.com', 1),
    ('Petr', 'Petrov', 'petr.petrov@example.com', 1),
    ('Anna', 'Smirnova', 'anna.smirnova@example.com', 2),
    ('Elena', 'Kuznetsova', 'elena.kuznetsova@example.com', 2),
    ('Dmitry', 'Sokolov', 'dmitry.sokolov@example.com', 3),
    ('Maria', 'Fedorova', 'maria.fedorova@example.com', 4);

-- ==========================
-- 3. Заполняем таблицу calendar_date
-- ==========================
-- Например, рабочие дни с 2026-02-01 по 2026-02-10
INSERT INTO calendar_date (date, is_working)
VALUES
    ('2026-02-01', TRUE),
    ('2026-02-02', TRUE),
    ('2026-02-03', TRUE),
    ('2026-02-04', FALSE),
    ('2026-02-05', FALSE),
    ('2026-02-06', TRUE),
    ('2026-02-07', TRUE),
    ('2026-02-08', TRUE),
    ('2026-02-09', TRUE),
    ('2026-02-10', TRUE);

-- ==========================
-- 4. Заполняем таблицу grades
-- ==========================
-- Для этого примера ставим случайные оценки от 2 до 5
INSERT INTO grades (grade, student_id, subject_name, date_id)
VALUES
    (5, 1, 'MATH', 1),
    (4, 1, 'PHYSICS', 2),
    (3, 2, 'CHEMISTRY', 1),
    (5, 2, 'BIOLOGY', 3),
    (4, 3, 'HISTORY', 4),
    (3, 3, 'LITERATURE', 5),
    (5, 4, 'MATH', 6),
    (2, 4, 'PHYSICS', 7),
    (4, 5, 'CHEMISTRY', 8),
    (5, 5, 'BIOLOGY', 9),
    (3, 6, 'HISTORY', 10),
    (4, 6, 'LITERATURE', 1);