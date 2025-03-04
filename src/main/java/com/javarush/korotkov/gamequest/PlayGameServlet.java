package com.javarush.korotkov.gamequest;

import com.javarush.korotkov.gamequest.service.ProvidingDataService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/playGame")
public class PlayGameServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        StatisticService statisticService = (StatisticService) session.getAttribute("statisticService");
        statisticService.increasePlayerScore();
        ProvidingDataService dataService = (ProvidingDataService) session.getAttribute("dataService");
        String nextText = dataService.getNextText();
        if (dataService.getCounter() > 5) { //Если выиграл
            log.info("Player won the game with score: {} and redirected to GameOverServlet", dataService.getCounter());
            context.getRequestDispatcher("/gameOver").forward(request, resp);
        } else {
            log.info("Game continues. Current step: {} and redirected to PlayGameServlet", dataService.getCounter());
            session.setAttribute("gameText", nextText);
            session.setAttribute("gameImage", dataService.getImageName());
            context.getRequestDispatcher("/playGame.jsp").forward(request, resp);
        }
    }
}
