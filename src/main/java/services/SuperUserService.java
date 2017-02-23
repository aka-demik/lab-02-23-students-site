package services;

import models.SuperUser;
import models.dao.Connector;
import models.dao.SuperUserDAO;
import models.dao.exceptions.PersistentException;

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
}
