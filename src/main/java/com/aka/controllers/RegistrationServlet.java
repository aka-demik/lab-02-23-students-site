package com.aka.controllers;

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

@WebServlet(urlPatterns = {"/registration", "/registration/"})
public class RegistrationServlet extends HttpServlet {
    static private Logger logger = Logger.getLogger(RegistrationServlet.class);
//    private SuperUserService superUserService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

//    @Autowired
//    public void setSuperUserService(SuperUserService superUserService) {
//        this.superUserService = superUserService;
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//
//        try {
//            superUserService.registerUser(login, password);
//
//            resp.sendRedirect(req.getServletContext().getContextPath() + "/list");
//        } catch (PersistentException e) {
//            req.setAttribute("error", "проблемы с СУБД");
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        } catch (Exception e) {
//            logger.error("", e);
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        }
    }
}
