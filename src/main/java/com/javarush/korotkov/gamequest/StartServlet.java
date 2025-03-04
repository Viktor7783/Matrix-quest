package com.javarush.korotkov.gamequest;

import java.io.*;

import com.javarush.korotkov.gamequest.models.Player;
import com.javarush.korotkov.gamequest.service.ProvidingDataService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "startServlet", value = "/start-servlet")
public class StartServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProvidingDataService dataService = new ProvidingDataService();
        log.info("Service: {} has been initialized", ProvidingDataService.class.getSimpleName());
        String username = request.getParameter("playerName");
        log.info("New game session started for player: {}", username);
        StatisticService statisticService = new StatisticService(new Player(username));
        log.info("Service: {} initialized for player: {}", StatisticService.class.getSimpleName(), username);
        session.setAttribute("dataService", dataService);
        session.setAttribute("gameText", dataService.getNextText());
        session.setAttribute("gameImage", dataService.getImageName());
        session.setAttribute("statisticService", statisticService);
        log.info("Session attributes set for player: {}. Initial game state prepared.", username);

        getServletContext().getRequestDispatcher("/playGame.jsp").forward(request, response);
    }
}