package services;

import models.dao.Connector;
import models.dao.UserDAO;
import models.dao.exceptions.PersistentException;
import models.pojo.User;

import java.util.Collection;

public class UserService {
    public static Collection<User> getUsers() throws PersistentException {
        UserDAO dao = new UserDAO(Connector.get());
        return dao.getAll();
    }
}
