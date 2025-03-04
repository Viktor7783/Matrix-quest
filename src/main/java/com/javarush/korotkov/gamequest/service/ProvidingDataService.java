package com.javarush.korotkov.gamequest.service;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

import static com.javarush.korotkov.gamequest.constants.WebConstants.*;

@Getter
public class ProvidingDataService {
    private final HashMap<Integer, List<String>> gamePlayData = new HashMap<>();
    private int counter;

    public ProvidingDataService() {
        gamePlayData.put(1, List.of(CHOOSE_TABLET, "choose_tablet"));
        gamePlayData.put(2, List.of(WELCOME_IN_REAL_WORLD, "welcome"));
        gamePlayData.put(3, List.of(RELEASE_YOUR_MIND, "release_mind"));
        gamePlayData.put(4, List.of(GO_TO_PIFIA, "go_to_pifia"));
        gamePlayData.put(5, List.of(SAVE_MORFIUS, "save_morfius"));
        gamePlayData.put(6, List.of(WAKE_UP_IN_BED, "wake_up"));
        gamePlayData.put(7, List.of(DROP_OUT_FROM_SHIP, "drop_out"));
        gamePlayData.put(8, List.of(VICTORY, "victory"));
    }


    public String getNextText() { // Для красной кнопки и игры
        return gamePlayData.get(++counter).getFirst();
    }

    public String getImageName() {
        return gamePlayData.get(counter).getLast(); // после getNextText()
    }

    public String getGamePlayText(int id) { // Для синей кнопки и game over
        return gamePlayData.get(id).getFirst();
    }

    public String getGamePlayImageName(int id) {
        return gamePlayData.get(id).getLast();
    }
}
