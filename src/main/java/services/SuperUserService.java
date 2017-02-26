package services;

import models.dao.CallReasonDAO;
import models.dao.Connector;
import models.dao.ScheduledCallDAO;
import models.dao.SuperUserDAO;
import models.dao.exceptions.PersistentException;
import models.pojo.CallReason;
import models.pojo.ScheduledCall;
import models.pojo.SuperUser;

import java.util.ArrayList;
import java.util.Collection;

public class SuperUserService {

    public static boolean authorize(String login, String password) throws PersistentException {
        SuperUserDAO uda = new SuperUserDAO(Connector.get());
        return uda.getByLogin(login, password) != null;
    }

    public static boolean registerUser(String login, String password) throws PersistentException {
        SuperUserDAO uda = new SuperUserDAO(Connector.get());
        SuperUser u = new SuperUser();
        u.setUserLogin(login);
        u.setUserPassword(password);
        u.setFirstName("Иван");
        u.setLastName("Иванов");
        u.setEmail("иванов@super.ru");
        return uda.insert(u);
    }

    public static void deleteScheduledCall(long id) throws PersistentException {
        ScheduledCallDAO dao = new ScheduledCallDAO(Connector.get());
        dao.deleteByID(id);
    }

    public static Collection<Lecture> getLectures() throws PersistentException {
        ScheduledCallDAO sdao = new ScheduledCallDAO(Connector.get());
        CallReasonDAO cdao = new CallReasonDAO(Connector.get());
        Collection<Lecture> list = new ArrayList<>();

        for (ScheduledCall c : sdao.getAll()) {
            list.add(new Lecture(c, cdao.getByID(c.getCall().getCallReasonId())));
        }
        return list;
    }

    public static Lecture getLectureByID(long id) throws PersistentException {
        ScheduledCallDAO sdao = new ScheduledCallDAO(Connector.get());
        CallReasonDAO cdao = new CallReasonDAO(Connector.get());

        ScheduledCall c = sdao.getByID(id);
        if (c != null) {
            return new Lecture(c, cdao.getByID(c.getCall().getCallReasonId()));
        } else {
            return null;
        }
    }

    public static class Lecture {
        private ScheduledCall schedule;
        private CallReason reason;

        public Lecture(ScheduledCall schedule, CallReason reason) {
            this.schedule = schedule;
            this.reason = reason;
        }

        public ScheduledCall getSchedule() {
            return schedule;
        }

        public CallReason getReason() {
            return reason;
        }
    }
}
