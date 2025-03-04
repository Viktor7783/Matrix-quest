import com.javarush.korotkov.gamequest.models.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;
import static com.javarush.korotkov.gamequest.models.enums.Status.*;

class StatusTest {

    @Test
    void testStatusValues() {
        Status[] statuses = Status.values();
        assertAll("Проверяем наличие всех статусов",
                () -> assertEquals(3, statuses.length),
                () -> assertTrue(contains(ALIVE)),
                () -> assertTrue(contains(DEAD)),
                () -> assertTrue(contains(WINNER)));
    }

    @ParameterizedTest
    @EnumSource(Status.class)
    void testStatusConditions(Status status) {
        assertNotNull(status.getCondition());
    }

    @Test
    void testSpecificConditions() {
        assertAll("Проверяем соответствие статуса и состояния игрока",
                () -> assertEquals("Еще живой", ALIVE.getCondition()),
                () -> assertEquals("Помер", DEAD.getCondition()),
                () -> assertEquals("Прошёл весь квест!", WINNER.getCondition()));
    }

    private boolean contains(Status status) {
        for (Status s : Status.values()) {
            if (s == status) {
                return true;
            }
        }
        return false;
    }
}