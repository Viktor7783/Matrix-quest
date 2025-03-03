package com.javarush.korotkov.gamequest.models.enums;

import lombok.Getter;

@Getter
public enum Status {
    ALIVE("Еще живой"),
    DEAD("Помер"),
    WINNER("Прошёл весь квест!");

    Status(String condition) {
        this.condition = condition;
    }

    private final String condition;
}
