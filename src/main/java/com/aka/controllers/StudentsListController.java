package com.aka.controllers;

import com.aka.dao.exceptions.PersistentException;
import com.aka.services.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentsListController {
    private static Logger logger = Logger.getLogger(StudentsListController.class);
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/students/list", method = RequestMethod.GET)
    public String doGet(Model model) {
        try {
            model.addAttribute("list", userService.getUsers());
            return "/students-list";
        } catch (PersistentException e) {
            model.addAttribute("error", "проблемы с СУБД");
            return "error";
        } catch (Exception e) {
            logger.error("", e);
            return "error";
        }
    }
}
