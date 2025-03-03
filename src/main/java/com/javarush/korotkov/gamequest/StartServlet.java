package com.javarush.korotkov.gamequest;

import java.io.*;

import com.javarush.korotkov.gamequest.models.Player;
import com.javarush.korotkov.gamequest.service.ProvidingTextService;
import com.javarush.korotkov.gamequest.service.StatisticService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "startServlet", value = "/start-servlet")
public class StartServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProvidingTextService textService = new ProvidingTextService();
        String username = request.getParameter("playerName");
        StatisticService statisticService = new StatisticService(new Player(username));

        session.setAttribute("textService", textService);
        session.setAttribute("gameText", textService.getNextText());
        session.setAttribute("gameImage", textService.getImageName());
        session.setAttribute("statisticService", statisticService);

        getServletContext().getRequestDispatcher("/playGame.jsp").forward(request, response);
    }
}