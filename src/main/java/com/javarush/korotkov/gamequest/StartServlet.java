package com.javarush.korotkov.gamequest;

import java.io.*;

import com.javarush.korotkov.gamequest.models.Player;
import com.javarush.korotkov.gamequest.service.ProvidingDataService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "startServlet", value = "/start-servlet")
public class StartServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProvidingDataService dataService = new ProvidingDataService();
        String username = request.getParameter("playerName");
        StatisticService statisticService = new StatisticService(new Player(username));

        session.setAttribute("dataService", dataService);
        session.setAttribute("gameText", dataService.getNextText());
        session.setAttribute("gameImage", dataService.getImageName());
        session.setAttribute("statisticService", statisticService);

        getServletContext().getRequestDispatcher("/playGame.jsp").forward(request, response);
    }
}