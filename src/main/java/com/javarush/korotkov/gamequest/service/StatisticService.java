package com.javarush.korotkov.gamequest.service;

import com.javarush.korotkov.gamequest.models.Player;
import com.javarush.korotkov.gamequest.models.enums.Status;
import lombok.Getter;

import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
public class StatisticService {
    private final Player player;
    private static final ConcurrentLinkedQueue<Player> players = new ConcurrentLinkedQueue<>();


    public StatisticService(Player player) {
        this.player = player;
        players.add(player);
    }

    public void setPlayerStatus(Status status) {
        player.setStatus(status);
    }

    public String getPlayerStatistic() {
        return player.toString();
    }

    public void increasePlayerScore() {
        player.increaseScore();
    }

    public String getPlayersRating() {
        StringBuilder sb = new StringBuilder();
        players.stream().sorted().forEach(player -> sb.append(player.toString()).append("<br><br>"));
        return sb.toString();
    }

}
