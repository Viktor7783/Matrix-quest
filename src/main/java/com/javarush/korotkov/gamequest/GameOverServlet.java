package com.javarush.korotkov.gamequest;


import com.javarush.korotkov.gamequest.service.ProvidingDataService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


import static com.javarush.korotkov.gamequest.models.enums.Status.DEAD;
import static com.javarush.korotkov.gamequest.models.enums.Status.WINNER;

@WebServlet("/gameOver")
public class GameOverServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = request.getSession();
        StatisticService statisticService = (StatisticService) session.getAttribute("statisticService");
        statisticService.setPlayerStatus(DEAD);

        ProvidingDataService dataService = (ProvidingDataService) session.getAttribute("dataService");
        if (dataService.getCounter() > 5) { // Если выиграли
            statisticService.setPlayerStatus(WINNER);
            request.setAttribute("gameText", dataService.getGamePlayText(8));
            request.setAttribute("gameOver", dataService.getGamePlayImageName(8));
        } else if (dataService.getCounter() == 1) { // Если съели в начале синюю таблетку
            request.setAttribute("gameText", dataService.getGamePlayText(6));
            request.setAttribute("gameOver", dataService.getGamePlayImageName(6));
        } else { // Если проиграли по ходу игры
            request.setAttribute("gameText", dataService.getGamePlayText(7));
            request.setAttribute("gameOver", dataService.getGamePlayImageName(7));
        }

        request.setAttribute("playerStatistic", statisticService.getPlayerStatistic());
        request.setAttribute("gameRatings", statisticService.getPlayersRating());

        session.invalidate();
        getServletContext().getRequestDispatcher("/gameOver.jsp").forward(request, resp);
    }
}

