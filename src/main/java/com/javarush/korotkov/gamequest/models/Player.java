package com.javarush.korotkov.gamequest.models;

import com.javarush.korotkov.gamequest.models.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static com.javarush.korotkov.gamequest.models.enums.Status.ALIVE;

@Getter
public class Player implements Comparable<Player> {
    private static volatile int count;
    private final int id;
    private final String name;
    private int score;
    @Setter
    private Status status;

    public Player(String name) {
        this.name = name;
        id = increaseAndGetCount();
        setStatus(ALIVE);
    }

    public void increaseScore() {
        score++;
    }

    private synchronized int increaseAndGetCount() {
        return ++count;
    }

    @Override
    public String toString() {
        return "Игрок: " + name + "<br>" + "id : " + id + "<br>" + "Счёт: " + score + "<br>" + "Статус: " + status.getCondition();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Player player)) return false;
        return Objects.equals(name, player.name) && Objects.equals(score, player.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, id);
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.score, this.score);
    }
}
