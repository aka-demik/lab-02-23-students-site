package services;

import models.dao.Connector;
import models.dao.UserDataObject;
import models.pojo.User;

import java.sql.SQLException;

public class UserService {

    public static boolean authorize(String login, String password) {
        UserDataObject uda = new UserDataObject(Connector.get());
        return uda.getByLogin(login, password) != null;
    }

    public static boolean registerUser(String login, String password) throws SQLException {
        UserDataObject uda = new UserDataObject(Connector.get());
        User u = new User();
        u.setUserLogin(login);
        u.setUserPassword(password);
        u.setFirstName("Иван");
        u.setLastName("Иванов");
        u.setEmail("Иванов@super.ru");
        return uda.insert(u);
    }
}
