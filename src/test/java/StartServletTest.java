import com.javarush.korotkov.gamequest.StartServlet;
import com.javarush.korotkov.gamequest.service.ProvidingDataService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static com.javarush.korotkov.gamequest.constants.WebConstants.CHOOSE_TABLET;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StartServletTest {
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

    private StartServlet startServlet;

    @BeforeEach
    void setUp() throws ServletException {
        startServlet = new StartServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        startServlet.init(servletConfig);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("playerName")).thenReturn("TestPlayer");
        when(servletContext.getRequestDispatcher("/playGame.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    void testDoPostWithValidPlayerName() throws ServletException, IOException {
        startServlet.doPost(request, response);

        verify(session).setAttribute(eq("dataService"), any(ProvidingDataService.class));
        verify(session).setAttribute(eq("gameText"), notNull());
        verify(session).setAttribute(eq("gameImage"), notNull());
        verify(session).setAttribute(eq("statisticService"), any(StatisticService.class));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPostWithEmptyPlayerName() throws ServletException, IOException {
        when(request.getParameter("playerName")).thenReturn("");
        startServlet.doPost(request, response);
        verify(session).setAttribute(eq("statisticService"), argThat(arg -> ((StatisticService) arg).getPlayer().getName().isEmpty()));
    }

    @Test
    void testDoPostWithNullPlayerName() throws ServletException, IOException {
        when(request.getParameter("playerName")).thenReturn(null);
        startServlet.doPost(request, response);
        verify(session).setAttribute(eq("statisticService"), argThat(arg -> ((StatisticService) arg).getPlayer().getName() == null));
    }

    @Test
    void testProvidingDataServiceInitialization() throws ServletException, IOException {
        startServlet.doPost(request, response);

        verify(session).setAttribute(eq("dataService"), any(ProvidingDataService.class));
        verify(session).setAttribute(eq("gameText"), eq(CHOOSE_TABLET));
        verify(session).setAttribute(eq("gameImage"), eq("choose_tablet"));
    }

    @Test
    void testForwardToCorrectPage() throws ServletException, IOException {
        startServlet.doPost(request, response);

        verify(servletContext).getRequestDispatcher("/playGame.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}