package com.danilskryl.petprojects.library.servlets;

import com.danilskryl.petprojects.library.model.User;
import com.danilskryl.petprojects.library.repository.LibraryManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private final LibraryManager dbManager = LibraryManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (dbManager.isUserExist(login, password)) {
            User user = dbManager.getUserByLoginAndPassword(login, password);

            Cookie cookie = new Cookie("id", user.getId().toString());
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            resp.addCookie(cookie);

            resp.sendRedirect("cabinet.jsp");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
