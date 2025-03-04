import com.javarush.korotkov.gamequest.models.Player;
import com.javarush.korotkov.gamequest.service.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.javarush.korotkov.gamequest.models.enums.Status.DEAD;
import static com.javarush.korotkov.gamequest.models.enums.Status.WINNER;
import static org.junit.jupiter.api.Assertions.*;

public class StatisticServiceTest {
    private StatisticService statisticService;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer");
        statisticService = new StatisticService(player);
    }

    @Test
    void testSetPlayerStatus() {
        assertAll("Проверяем на неверный статус",
                () -> {
                    statisticService.setPlayerStatus(DEAD);
                    assertEquals(DEAD, player.getStatus());
                },
                () -> {
                    statisticService.setPlayerStatus(WINNER);
                    assertEquals(WINNER, player.getStatus());
                }
        );
    }

    @Test
    void testGetPlayerStatistic() {
        String statistic = statisticService.getPlayerStatistic();
        assertAll("Проверяем все поля игрока",
                () -> assertNotNull(statistic),
                () -> assertTrue(statistic.contains("TestPlayer")),
                () -> assertTrue(statistic.contains("Счёт: 0")),
                () -> assertTrue(statistic.contains("Статус: Еще живой"))
        );
    }

    @Test
    void testIncreasePlayerScore() {
        statisticService.increasePlayerScore();
        assertEquals(1, player.getScore());
        statisticService.increasePlayerScore();
        assertEquals(2, player.getScore());
    }

    @Test
    void testGetPlayersRating() {
        Player player2 = new Player("Player2");
        StatisticService service2 = new StatisticService(player2);

        statisticService.increasePlayerScore();
        service2.increasePlayerScore();
        service2.increasePlayerScore();

        String rating = statisticService.getPlayersRating();
        assertNotNull(rating);
        assertTrue(rating.contains("Player2"));
        assertTrue(rating.contains("TestPlayer"));
        assertTrue(rating.indexOf("Счёт: 2") < rating.indexOf("Счёт: 1")); // Проверяем сортировку
    }
}
