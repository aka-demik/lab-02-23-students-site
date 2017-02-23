package controllers;

import models.dao.exceptions.PersistentException;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListServlet extends HttpServlet {
    Logger logger = Logger.getLogger(ListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("list", UserService.getUsers());
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        } catch (PersistentException e) {
            req.setAttribute("error", "проблемы с СУБД");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error("", e);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
