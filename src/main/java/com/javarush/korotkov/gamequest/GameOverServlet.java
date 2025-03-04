package com.javarush.korotkov.gamequest;


import com.javarush.korotkov.gamequest.service.ProvidingDataService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


import static com.javarush.korotkov.gamequest.models.enums.Status.DEAD;
import static com.javarush.korotkov.gamequest.models.enums.Status.WINNER;

@Slf4j //Чтобы писать свои логи
@WebServlet("/gameOver")
public class GameOverServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = request.getSession();
        StatisticService statisticService = (StatisticService) session.getAttribute("statisticService");
        statisticService.setPlayerStatus(DEAD);

        ProvidingDataService dataService = (ProvidingDataService) session.getAttribute("dataService");
        if (dataService.getCounter() > 5) { // Если выиграли
            log.info("Player won the game with score: {}", dataService.getCounter());
            statisticService.setPlayerStatus(WINNER);
            request.setAttribute("gameText", dataService.getGamePlayText(8));
            request.setAttribute("gameOver", dataService.getGamePlayImageName(8));
        } else if (dataService.getCounter() == 1) { // Если съели в начале синюю таблетку
            log.info("Player chose blue pill at the start");
            request.setAttribute("gameText", dataService.getGamePlayText(6));
            request.setAttribute("gameOver", dataService.getGamePlayImageName(6));
        } else { // Если проиграли по ходу игры
            log.info("Player died during the game at step: {}", dataService.getCounter());
            request.setAttribute("gameText", dataService.getGamePlayText(7));
            request.setAttribute("gameOver", dataService.getGamePlayImageName(7));
        }

        request.setAttribute("playerStatistic", statisticService.getPlayerStatistic());
        request.setAttribute("gameRatings", statisticService.getPlayersRating());
        log.info("Final player statistics: {}", statisticService.getPlayerStatistic());
        session.invalidate();
        getServletContext().getRequestDispatcher("/gameOver.jsp").forward(request, resp);
    }
}

