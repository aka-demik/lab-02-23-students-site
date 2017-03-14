package com.aka.controllers;

import com.aka.services.interfaces.LecturesService;
import com.aka.services.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LecturesListController {
    private static Logger logger = Logger.getLogger(LecturesListController.class);
    private LecturesService lecturesService;

    @Autowired
    public void setLecturesService(LecturesService lecturesService) {
        this.lecturesService = lecturesService;
    }

    @RequestMapping(path = "/lectures/delete", method = RequestMethod.POST)
    public String deleteLecture(
            Model model,
            @RequestAttribute("id") long id) {
        try {
            lecturesService.deleteScheduledCall(id);
            return "redirect:/lectures";
        } catch (ServiceException e) {
            model.addAttribute("error", "проблемы с СУБД");
            return  "/error";
        } catch (Exception e) {
            logger.error("", e);
            return  "/error";
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "/lectures", method = RequestMethod.GET)
    public String showList(Model model) {
        try {
            model.addAttribute("list", lecturesService.getLectures());
            return  "/lectures-list";
        } catch (ServiceException e) {
            model.addAttribute("error", "проблемы с СУБД");
            return  "/error";
        } catch (Exception e) {
            logger.error("", e);
            return  "/error";
        }
    }
}

