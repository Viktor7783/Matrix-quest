import com.javarush.korotkov.gamequest.PlayGameServlet;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayGameServletTest {
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
    private StatisticService statisticService;
    @Mock
    private ProvidingDataService dataService;
    @Mock
    private ServletConfig servletConfig;

    private PlayGameServlet playGameServlet;

    @BeforeEach
    void setUp() throws ServletException {
        playGameServlet = new PlayGameServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        playGameServlet.init(servletConfig);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("statisticService")).thenReturn(statisticService);
        when(session.getAttribute("dataService")).thenReturn(dataService);
        when(playGameServlet.getServletContext()).thenReturn(servletContext);
    }

    @Test
    void testWinningScenario() throws ServletException, IOException {
        when(dataService.getCounter()).thenReturn(6);
        when(servletContext.getRequestDispatcher("/gameOver")).thenReturn(requestDispatcher);

        playGameServlet.doPost(request, response);

        verify(statisticService).increasePlayerScore();
        verify(dataService).getNextText();
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testContinuePlayingScenario() throws ServletException, IOException {
        String nextText = "Next game text";
        String imageName = "next_image";

        when(dataService.getCounter()).thenReturn(3);
        when(dataService.getNextText()).thenReturn(nextText);
        when(dataService.getImageName()).thenReturn(imageName);
        when(servletContext.getRequestDispatcher("/playGame.jsp")).thenReturn(requestDispatcher);

        playGameServlet.doPost(request, response);

        verify(statisticService).increasePlayerScore();
        verify(session).setAttribute("gameText", nextText);
        verify(session).setAttribute("gameImage", imageName);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testFirstMoveScenario() throws ServletException, IOException {
        when(dataService.getCounter()).thenReturn(1);
        when(servletContext.getRequestDispatcher("/playGame.jsp")).thenReturn(requestDispatcher);

        playGameServlet.doPost(request, response);

        verify(statisticService).increasePlayerScore();
        verify(dataService).getNextText();
        verify(dataService).getImageName();
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testLastMoveBeforeWinScenario() throws ServletException, IOException {
        when(dataService.getCounter()).thenReturn(5);
        when(servletContext.getRequestDispatcher("/playGame.jsp")).thenReturn(requestDispatcher);

        playGameServlet.doPost(request, response);

        verify(statisticService).increasePlayerScore();
        verify(dataService).getNextText();
        verify(dataService).getImageName();
        verify(requestDispatcher).forward(request, response);
    }
}
