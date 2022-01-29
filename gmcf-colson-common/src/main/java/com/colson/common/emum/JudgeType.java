package com.colson.common.emum;

public enum JudgeType {
    NONE,
    LLIKE,
    RLIKE,
    LIKE,
    NOTLIKE,
    EQ,
    NE,
    GE,
    GT,
    LE,
    LT,
    IN,
    NOTIN,
    BETWEEN,
    ISNULL,
    NOTNULL,
    JSON_EQ,
    JSON_EXISTS;

    private JudgeType() {
    }
}
