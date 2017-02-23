package models.dao;

import models.SuperUser;
import models.dao.exceptions.SuperUserDAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperUserDAO extends AbstractDAO<SuperUser> {
    static private Logger logger = Logger.getLogger(SuperUserDAO.class);

    public SuperUserDAO(Connection con) {
        super(con);
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT " +
                "id, email, firstname, lastname, middlename, user_login, user_password " +
                "FROM superuser";
    }

    @Override
    protected String getSelectOneSQL() {
        return "SELECT " +
                "id, email, firstname, middlename, lastname, user_login, user_password " +
                "FROM superuser " +
                "WHERE id = ?";
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO superuser " +
                "(id, email, firstname, middlename, lastname, user_login, user_password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateSQL() {
        return null;
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM superuser WHERE id = ?";
    }

    @Override
    protected String getDeleteAllSQL() {
        return "DELETE FROM superuser";
    }

    @Override
    protected void prepareInsert(SuperUser obj, PreparedStatement statement) throws SuperUserDAOException {
        try {
            statement.setLong(1, obj.getId());
            statement.setString(2, obj.getEmail());
            statement.setString(3, obj.getFirstName());
            statement.setString(4, obj.getMiddleName());
            statement.setString(5, obj.getLastName());
            statement.setString(6, obj.getUserLogin());
            statement.setString(7, obj.getUserPassword());
        } catch (SQLException e) {
            logger.error("", e);
            throw new SuperUserDAOException(e);
        }
    }

    @Override
    protected void prepareUpdate(SuperUser obj, PreparedStatement statement) throws SuperUserDAOException {
        try {
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getFirstName());
            statement.setString(3, obj.getMiddleName());
            statement.setString(4, obj.getLastName());
            statement.setString(5, obj.getUserLogin());
            statement.setString(6, obj.getUserPassword());
            statement.setLong(7, obj.getId());
        } catch (SQLException e) {
            logger.error("", e);
            throw new SuperUserDAOException(e);
        }
    }

    @Override
    protected SuperUser createObj() {
        return new SuperUser();
    }

    @Override
    protected SuperUser readObj(SuperUser obj, ResultSet source) throws SuperUserDAOException {
        try {
            obj.setId(source.getLong(1));
            obj.setEmail(source.getString(2));
            obj.setFirstName(source.getString(3));
            obj.setMiddleName(source.getString(4));
            obj.setLastName(source.getString(5));
            obj.setUserLogin(source.getString(6));
            obj.setUserPassword(source.getString(7));
        } catch (SQLException e) {
            logger.error("", e);
            throw new SuperUserDAOException(e);
        }
        return obj;
    }

    public SuperUser getByLogin(String login, String pass) throws SuperUserDAOException {
        try (PreparedStatement statement = con.prepareStatement(getSelectAllSQL() +
                " WHERE user_login = ? AND user_password = ?")) {
            statement.setString(1, login);
            statement.setString(2, pass);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return readObj(createObj(), result);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("", e);
            throw new SuperUserDAOException(e);
        }
    }

}
