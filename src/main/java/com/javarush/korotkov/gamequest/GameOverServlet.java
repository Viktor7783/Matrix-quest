package com.javarush.korotkov.gamequest;


import com.javarush.korotkov.gamequest.service.ProvidingTextService;
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

        ProvidingTextService textService = (ProvidingTextService) session.getAttribute("textService");
        if (textService.getCounter() > 5) { // Если выиграли
            statisticService.setPlayerStatus(WINNER);
            request.setAttribute("gameText", textService.getGamePlayText(8));
            request.setAttribute("gameOver", textService.getGamePlayImageName(8));
        } else if (textService.getCounter() == 1) { // Если съели в начале синюю таблетку
            request.setAttribute("gameText", textService.getGamePlayText(6));
            request.setAttribute("gameOver", textService.getGamePlayImageName(6));
        } else { // Если проиграли по ходу игры
            request.setAttribute("gameText", textService.getGamePlayText(7));
            request.setAttribute("gameOver", textService.getGamePlayImageName(7));
        }

        request.setAttribute("playerStatistic", statisticService.getPlayerStatistic());
        request.setAttribute("gameRatings", statisticService.getPlayersRating());

        session.invalidate();
        getServletContext().getRequestDispatcher("/gameOver.jsp").forward(request, resp);
    }
}

