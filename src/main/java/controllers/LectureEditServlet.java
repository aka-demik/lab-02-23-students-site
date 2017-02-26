package controllers;

import models.dao.CallReasonDAO;
import models.dao.Connector;
import models.dao.exceptions.PersistentException;
import org.apache.log4j.Logger;
import services.SuperUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/lectures/edit", "/lectures/edit/"})
public class LectureEditServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LecturesListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sid = req.getParameter("id");
            long id = Long.parseLong(sid);
            SuperUserService.Lecture lecture = SuperUserService.getLectureByID(id);
            if (lecture == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            req.setAttribute("lecture", lecture);
            req.setAttribute("lectures", new CallReasonDAO(Connector.get()).getAll());
            req.getRequestDispatcher("/lecture-edit.jsp").forward(req, resp);

        } catch (PersistentException e) {
            req.setAttribute("error", "проблемы с СУБД");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error("", e);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
