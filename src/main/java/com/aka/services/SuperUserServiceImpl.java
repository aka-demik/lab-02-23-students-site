package com.aka.services;

import com.aka.dao.CallReasonDAO;
import com.aka.dao.LecturesDAO;
import com.aka.dao.SuperUserDAO;
import com.aka.dao.exceptions.PersistentException;
import com.aka.models.CallReason;
import com.aka.models.ScheduledLecture;
import com.aka.models.SuperUser;
import com.aka.services.interfaces.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SuperUserServiceImpl implements SuperUserService {
    private SuperUserDAO superUserDAO;
    private LecturesDAO lecturesDAO;
    private CallReasonDAO callReasonDAO;

    @Autowired
    public void setLecturesDAO(LecturesDAO lecturesDAO) {
        this.lecturesDAO = lecturesDAO;
    }

    @Autowired
    public void setCallReasonDAO(CallReasonDAO callReasonDAO) {
        this.callReasonDAO = callReasonDAO;
    }

    @Autowired
    public void setSuperUserDAO(SuperUserDAO superUserDAO) {
        this.superUserDAO = superUserDAO;
    }

    @Override
    public boolean authorize(String login, String password) throws PersistentException {
        return superUserDAO.getByLogin(login, password) != null;
    }

    @Override
    public boolean registerUser(String login, String password) throws PersistentException {
        SuperUser u = new SuperUser();
        u.setUserLogin(login);
        u.setUserPassword(password);
        u.setFirstName("Иван");
        u.setLastName("Иванов");
        u.setEmail("иванов@super.ru");
        return superUserDAO.insert(u);
    }

    @Override
    public void deleteScheduledCall(long id) throws PersistentException {
        superUserDAO.deleteByID(id);
    }

    @Override
    public Collection<Lecture> getLectures() throws PersistentException {
        Collection<Lecture> list = new ArrayList<>();

        for (ScheduledLecture c : lecturesDAO.getAll()) {
            list.add(new Lecture(c, callReasonDAO.getByID(c.getLecture().getCallReasonId())));
        }
        return list;
    }

    @Override
    public Lecture getLectureByID(long id) throws PersistentException {
        ScheduledLecture c = lecturesDAO.getByID(id);
        if (c != null) {
            return new Lecture(c, callReasonDAO.getByID(c.getLecture().getCallReasonId()));
        } else {
            return null;
        }
    }

    public class Lecture {
        private ScheduledLecture schedule;
        private CallReason reason;

        public Lecture(ScheduledLecture schedule, CallReason reason) {
            this.schedule = schedule;
            this.reason = reason;
        }

        public ScheduledLecture getSchedule() {
            return schedule;
        }

        public CallReason getReason() {
            return reason;
        }
    }
}
