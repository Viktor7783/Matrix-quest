import com.javarush.korotkov.gamequest.service.ProvidingDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProvidingDataServiceTest {
    private ProvidingDataService service;
    private HashMap<Integer, List<String>> gamePlayData;

    @BeforeEach
    void setUp() {
        service = new ProvidingDataService();
        gamePlayData = service.getGamePlayData();
    }

    @Test
    void testGetNextText() {
        String firstText = service.getNextText();
        assertNotNull(firstText);
    }

    @Test
    void testGetImageName() {
        service.getNextText(); // Увеличиваем счетчик
        String imageName = service.getImageName();
        assertNotNull(imageName);
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void testGetGamePlayText(int id) {

        String actualText = service.getGamePlayText(id);
        String expectedText = gamePlayData.get(id).getFirst();
        assertAll("Сравнение на null и на правильные значения",
                () -> assertNotNull(actualText),
                () -> assertEquals(expectedText, actualText)
        );

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void testGetGamePlayImageName(int id) {
        String actualImageName = service.getGamePlayImageName(id);
        String expectedImageName = gamePlayData.get(id).getLast();
        assertAll("Сравнение на null и на правильные значения",
                () -> assertNotNull(actualImageName),
                () -> assertEquals(expectedImageName, actualImageName)
        );
    }
}