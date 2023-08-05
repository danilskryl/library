package com.danilskryl.petprojects.library.servlets;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/library")
public class LibraryServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

//        Cookie[] cookies = request.getCookies();
//
//        if (cookies == null) {
//            response.sendRedirect("/login.jsp");
//        }
//
//        for (Cookie cookie : cookies) {
//
//        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "SUCCESSFUL" + "</h1>");
        out.println("</body></html>");
    }
}