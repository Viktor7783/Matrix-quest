package com.javarush.korotkov.gamequest;

import com.javarush.korotkov.gamequest.service.ProvidingTextService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/playGame")
public class PlayGameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        StatisticService statisticService = (StatisticService) session.getAttribute("statisticService");
        statisticService.increasePlayerScore();
        ProvidingTextService textService = (ProvidingTextService) session.getAttribute("textService");
        String nextText = textService.getNextText();
        if (textService.getCounter() > 5) { //Если выиграл
            context.getRequestDispatcher("/gameOver").forward(request, resp);
        } else {
            session.setAttribute("gameText", nextText);
            session.setAttribute("gameImage", textService.getImageName());
            context.getRequestDispatcher("/playGame.jsp").forward(request, resp);
        }
    }
}
