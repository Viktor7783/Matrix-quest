package com.javarush.korotkov.gamequest.service;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

import static com.javarush.korotkov.gamequest.constants.WebConstants.*;

public class ProvidingTextService {
    private HashMap<Integer, List<String>> gamePlayText = new HashMap<>();
    @Getter
    private int counter;

    public ProvidingTextService() {
        gamePlayText.put(1, List.of(CHOOSE_TABLET, "choose_tablet"));
        gamePlayText.put(2, List.of(WELCOME_IN_REAL_WORLD, "welcome"));
        gamePlayText.put(3, List.of(RELEASE_YOUR_MIND, "release_mind"));
        gamePlayText.put(4, List.of(GO_TO_PIFIA, "go_to_pifia"));
        gamePlayText.put(5, List.of(SAVE_MORFIUS, "save_morfius"));
        gamePlayText.put(6, List.of(WAKE_UP_IN_BED, "wake_up"));
        gamePlayText.put(7, List.of(DROP_OUT_FROM_SHIP, "drop_out"));
        gamePlayText.put(8, List.of(VICTORY, "victory"));
    }

    public String getNextText() { // Для красной кнопки и игры
        return gamePlayText.get(++counter).getFirst();
    }

    public String getImageName() {
        return gamePlayText.get(counter).getLast(); // после getNextText()
    }

    public String getGamePlayText(int id) { // Для синей кнопки и game over
        return gamePlayText.get(id).getFirst();
    }

    public String getGamePlayImageName(int id) {
        return gamePlayText.get(id).getLast();
    }

    public void resetCounter() { // Не забываем сбросить счетчик после победы или поражения, чтобы начать заново
        counter = 0;
    }
}
