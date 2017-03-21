package com.aka.controllers;

import com.aka.services.interfaces.UserService;
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

@WebServlet(urlPatterns = {"/students/list", "/students/list/"})
public class StudentsListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(StudentsListServlet.class);
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("list", userService.getUsers());
            req.getRequestDispatcher("/students-list.jsp").forward(req, resp);
//        } catch (PersistentException e) {
//            req.setAttribute("error", "проблемы с СУБД");
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error("", e);
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
