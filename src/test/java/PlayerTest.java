import com.javarush.korotkov.gamequest.models.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.javarush.korotkov.gamequest.models.enums.Status.*;

class PlayerTest {

    @Test
    void testPlayerCreation() {
        Player player = new Player("Neo");
        assertAll("Проверяем все поля игрока",
                () -> assertNotNull(player),
                () -> assertEquals("Neo", player.getName()),
                () -> assertEquals(ALIVE, player.getStatus()),
                () -> assertEquals(0, player.getScore()));
    }

    @Test
    void testIncreaseScore() {
        Player player = new Player("Neo");
        player.increaseScore();
        assertEquals(1, player.getScore());
        player.increaseScore();
        assertEquals(2, player.getScore());
    }

    @Test
    void testEquals() {
        Player player1 = new Player("Neo");
        Player player2 = new Player("Neo");
        Player player3 = new Player("Trinity");

        player1.increaseScore();
        player2.increaseScore();

        assertEquals(player1, player2);
        assertNotEquals(player1, player3);
    }

    @Test
    void testHashCode() {
        Player player1 = new Player("Neo");
        Player player2 = new Player("Neo");

        player1.increaseScore();
        player2.increaseScore();

        assertNotEquals(player1.hashCode(), player2.hashCode());
    }

    @Test
    void testCompareTo() {
        Player player1 = new Player("Neo");
        Player player2 = new Player("Trinity");

        player1.increaseScore(); // score = 1
        player2.increaseScore();
        player2.increaseScore(); // score = 2

        assertTrue(player1.compareTo(player2) > 0);
        assertTrue(player2.compareTo(player1) < 0);
    }

    @Test
    void testToString() {
        Player player = new Player("Neo");
        String playerString = player.toString();

        assertTrue(playerString.contains("Neo"));
        assertTrue(playerString.contains("Счёт: 0"));
        assertTrue(playerString.contains("Еще живой"));
    }
}