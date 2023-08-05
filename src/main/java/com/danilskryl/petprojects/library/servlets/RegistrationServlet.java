package com.danilskryl.petprojects.library.servlets;

import com.danilskryl.petprojects.library.model.User;
import com.danilskryl.petprojects.library.repository.LibraryManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final LibraryManager dbManager = LibraryManager.getInstance();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String date = req.getParameter("birthDate");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse(date);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setBooks(new ArrayList<>());
        user.setBirthDate(birthDate);

        dbManager.saveUser(user);

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
