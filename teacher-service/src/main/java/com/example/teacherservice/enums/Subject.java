package com.example.teacherservice.enums;

public enum Subject {
    MATH,
    PHYSICS,
    CHEMISTRY,
    BIOLOGY,
    HISTORY,
    LITERATURE;

    public static Subject fromString(String subjectEnum) {
        for (Subject subject : Subject.values()) {
            if (subject.name().equalsIgnoreCase(subjectEnum)) {
                return subject;
            }
        }
        throw new IllegalArgumentException("Unknown Subject " + subjectEnum);
    }
}



