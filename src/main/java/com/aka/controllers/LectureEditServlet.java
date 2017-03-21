package com.aka.controllers;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/lectures/edit", "/lectures/edit/"})
public class LectureEditServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LecturesListServlet.class);
//    private SuperUserService superUserService;
//    private CallReasonDAO callReasonDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

//    @Autowired
//    public void setSuperUserService(SuperUserService superUserService) {
//        this.superUserService = superUserService;
//    }

//    @Autowired
//    public void setCallReasonDAO(CallReasonDAO callReasonDAO) {
//        this.callReasonDAO = callReasonDAO;
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            String sid = req.getParameter("id");
//            long id = Long.parseLong(sid);
//            SuperUserServiceImpl.Lecture lecture = superUserService.getLectureByID(id);
//            if (lecture == null) {
//                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//                return;
//            }
//
//            req.setAttribute("lecture", lecture);
//            req.setAttribute("lectures", callReasonDAO.getAll());
//            req.getRequestDispatcher("/lecture-edit.jsp").forward(req, resp);
//
//        } catch (PersistentException e) {
//            req.setAttribute("error", "проблемы с СУБД");
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        } catch (Exception e) {
//            logger.error("", e);
//            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//        }
    }
}
