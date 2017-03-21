package com.aka.controllers;

import com.aka.services.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RegistrationServlet {
    static private Logger logger = Logger.getLogger(RegistrationServlet.class);
    private UserService userService;

    @Autowired
    public void setSuperUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    protected String doGet() {
        return "/registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    protected String doPost(
            @RequestParam(name = "login", required = true) String login,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "password", required = true) String password) {
        try {
            userService.registerUser(login, name, password);
            return "redirect:/user/list";
        } catch (Exception e) {
            logger.error("", e);
            return "/error";
        }
    }
}
