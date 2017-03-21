package com.aka.controllers;

import com.aka.services.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    static private Logger logger = Logger.getLogger(LoginController.class);

    private UserService userService;

    @Autowired
    public void setSuperUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(@SessionAttribute(name = "logged", required = false) boolean logged) {
        if (!logged) {
            return "login";
        } else {
            return "redirect:/lectures";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(
            Model model,
            HttpSession session,
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            @SessionAttribute(name = "logged", required = false) boolean logged) {

        try {
            if (!logged) {
                logged = userService.authorizeUser(login, password) != null;
                if (logged) {
                    session.setAttribute("logged", true);
                }
            }

            if (logged) {
                return "redirect:/lectures";
            } else {
                model.addAttribute("error", true);
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "проблемы с СУБД");
            return "error";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showLogout(HttpSession session) {
        session.setAttribute("logged", null);
        return "redirect:/login";
    }

}
