package com.javarush.korotkov.gamequest;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/restart")
public class RestartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Очищаем сессию
        HttpSession session = request.getSession();
        session.invalidate();
        // Перенаправляем на начальную страницу
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}

