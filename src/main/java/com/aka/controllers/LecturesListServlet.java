package com.aka.controllers;

import com.aka.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "lecturesList", urlPatterns = {"/lectures", "/lectures/"})
public class LecturesListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LecturesListServlet.class);
//    private LecturesService lecturesService;

//    @Autowired
//    public void setLecturesService(LecturesService lecturesService) {
//        this.lecturesService = lecturesService;
//    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            String sid = req.getParameter("id");
//            long l = Long.parseLong(sid);
//            lecturesService.deleteScheduledCall(l);
//            resp.sendRedirect(req.getContextPath() + "/lectures");
//        } catch (ServiceException e) {
//            req.setAttribute("error", "проблемы с СУБД");
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        } catch (Exception e) {
//            logger.error("", e);
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        }
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
//        try {
//            req.setAttribute("list", lecturesService.getLectures());
//            req.getRequestDispatcher("/lectures-list.jsp").forward(req, resp);
//        } catch (ServiceException e) {
//            req.setAttribute("error", "проблемы с СУБД");
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        } catch (Exception e) {
//            logger.error("", e);
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        }
    }
}

