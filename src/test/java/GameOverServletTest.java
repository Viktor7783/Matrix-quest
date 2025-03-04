import com.javarush.korotkov.gamequest.GameOverServlet;
import com.javarush.korotkov.gamequest.service.ProvidingDataService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.javarush.korotkov.gamequest.models.enums.Status.DEAD;
import static com.javarush.korotkov.gamequest.models.enums.Status.WINNER;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameOverServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private StatisticService statisticService;
    @Mock
    private ProvidingDataService dataService;

    private GameOverServlet gameOverServlet;

    @BeforeEach
    void setUp() throws ServletException {
        gameOverServlet = new GameOverServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        gameOverServlet.init(servletConfig);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("statisticService")).thenReturn(statisticService);
        when(session.getAttribute("dataService")).thenReturn(dataService);
        when(servletContext.getRequestDispatcher("/gameOver.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    void testWinnerScenario() throws ServletException, IOException {
        when(dataService.getCounter()).thenReturn(6);
        when(dataService.getGamePlayText(8)).thenReturn("Victory text");
        when(dataService.getGamePlayImageName(8)).thenReturn("victory_image");
        when(statisticService.getPlayerStatistic()).thenReturn("Player stats");
        when(statisticService.getPlayersRating()).thenReturn("Game ratings");

        gameOverServlet.doPost(request, response);

        verify(statisticService).setPlayerStatus(WINNER);
        verify(request).setAttribute("gameText", "Victory text");
        verify(request).setAttribute("gameOver", "victory_image");
        verify(request).setAttribute("playerStatistic", "Player stats");
        verify(request).setAttribute("gameRatings", "Game ratings");
        verify(session).invalidate();
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testBlueTabletScenario() throws ServletException, IOException {
        when(dataService.getCounter()).thenReturn(1);
        when(dataService.getGamePlayText(6)).thenReturn("Blue pill text");
        when(dataService.getGamePlayImageName(6)).thenReturn("blue_pill_image");

        gameOverServlet.doPost(request, response);

        verify(statisticService).setPlayerStatus(DEAD);
        verify(request).setAttribute("gameText", "Blue pill text");
        verify(request).setAttribute("gameOver", "blue_pill_image");
        verify(session).invalidate();
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testGameOverScenario() throws ServletException, IOException {
        when(dataService.getCounter()).thenReturn(3);
        when(dataService.getGamePlayText(7)).thenReturn("Game over text");
        when(dataService.getGamePlayImageName(7)).thenReturn("game_over_image");

        gameOverServlet.doPost(request, response);

        verify(statisticService).setPlayerStatus(DEAD);
        verify(request).setAttribute("gameText", "Game over text");
        verify(request).setAttribute("gameOver", "game_over_image");
        verify(session).invalidate();
        verify(requestDispatcher).forward(request, response);
    }
}
