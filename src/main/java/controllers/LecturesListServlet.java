package controllers;

import models.dao.exceptions.PersistentException;
import org.apache.log4j.Logger;
import services.SuperUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/lectures", "/lectures/"})
public class LecturesListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LecturesListServlet.class);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sid = req.getParameter("id");
            long l = Long.parseLong(sid);
            SuperUserService.deleteScheduledCall(l);
            resp.sendRedirect(req.getContextPath() + "/lectures");
        } catch (PersistentException e) {
            req.setAttribute("error", "проблемы с СУБД");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error("", e);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("delete".equals(req.getParameter("type")))
            doDelete(req, resp);
        else
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("list", SuperUserService.getLectures());
            req.getRequestDispatcher("/lectures-list.jsp").forward(req, resp);
        } catch (PersistentException e) {
            req.setAttribute("error", "проблемы с СУБД");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error("", e);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}

