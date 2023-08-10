package com.danilskryl.petprojects.library.servlets;

import com.danilskryl.petprojects.library.model.User;
import com.danilskryl.petprojects.library.repository.LibraryManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final LibraryManager dbManager = LibraryManager.getInstance();
    private final Logger LOG = LoggerFactory.getLogger(RegistrationServlet.class);

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LOG.debug("Registration started");

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
        user.setJoinDate(LocalDateTime.now());

        dbManager.saveUser(user);

        LOG.info("User [{}] created and saved", username);

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
