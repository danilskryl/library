package com.danilskryl.petprojects.library.servlets;


import com.danilskryl.petprojects.library.model.Book;
import com.danilskryl.petprojects.library.repository.LibraryManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BookControllerServlet", value = "/bookServlet")
public class BookController extends HttpServlet {
    private final LibraryManager dbManager = LibraryManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");

        Cookie[] cookies = req.getCookies();
        long id = 0;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                id = Long.parseLong(cookie.getValue());
                break;
            }
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        long bookId = dbManager.saveBook(book);

        dbManager.addBookToUser(id, bookId);

        resp.sendRedirect("cabinet.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long bookId = Long.parseLong(req.getParameter("bookId"));
        Long userId = Long.parseLong(req.getParameter("userId"));

        dbManager.removeBook(bookId, userId);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String jsonResponse = "{\"message\": \"Книга успешно удалена\"}";
        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long bookId = Long.parseLong(req.getParameter("bookId"));
        String title = req.getParameter("bookTitle");
        String author = req.getParameter("bookAuthor");

        dbManager.updateBook(bookId, title, author);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String jsonResponse = "{\"message\": \"Книга успешно обновлена\"}";
        resp.getWriter().write(jsonResponse);
    }
}
