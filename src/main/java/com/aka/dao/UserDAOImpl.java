package com.aka.dao;

import com.aka.dao.exceptions.UserDAOException;
import com.aka.models.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    protected String getSelectAllSQL() {
        return "SELECT " +
                "id, bitrix_id, firstname, middlename, lastname, email, phone, birthdate, user_login, user_password " +
                "FROM \"user\"";
    }

    @Override
    protected String getSelectOneSQL() {
        return "SELECT " +
                "id, bitrix_id, firstname, middlename, lastname, email, phone, birthdate, user_login, user_password " +
                "FROM \"user\"" +
                "WHERE id = ?";
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO \"user\" " +
                "(id, bitrix_id, firstname, middlename, lastname, email, phone, birthdate, user_login, user_password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE \"user\" SET " +
                "bitrix_id = ?, firstname = ?, middlename = ?, lastname = ?, " +
                "email = ?, phone = ?, birthdate = ?, user_login = ?, user_password = ?" +
                "WHERE id = ?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM \"user\" WHERE id = ?";
    }

    @Override
    protected String getDeleteAllSQL() {
        return "DELETE FROM \"user\"";
    }

    @Override
    protected void prepareInsert(User obj, PreparedStatement statement) throws UserDAOException {
        try {
            if (obj.getId() > 0)
                statement.setLong(1, obj.getId());
            else
                statement.setNull(1, Types.BIGINT);
            statement.setLong(2, obj.getBitrixId());
            statement.setString(3, obj.getFirstName());
            statement.setString(4, obj.getMiddleName());
            statement.setString(5, obj.getLastName());
            statement.setString(6, obj.getEmail());
            statement.setString(7, obj.getPhone());
            statement.setDate(8, obj.getBirthDate());
            statement.setString(9, obj.getUserLogin());
            statement.setString(10, obj.getUserPassword());
        } catch (SQLException e) {
            logger.error("", e);
            throw new UserDAOException(e);
        }
    }

    @Override
    protected void prepareUpdate(User obj, PreparedStatement statement) throws UserDAOException {
        try {
            statement.setLong(1, obj.getBitrixId());
            statement.setString(2, obj.getFirstName());
            statement.setString(3, obj.getMiddleName());
            statement.setString(4, obj.getLastName());
            statement.setString(5, obj.getEmail());
            statement.setString(6, obj.getPhone());
            statement.setDate(7, obj.getBirthDate());
            statement.setString(8, obj.getUserLogin());
            statement.setString(9, obj.getUserPassword());
            statement.setLong(10, obj.getId());
        } catch (SQLException e) {
            logger.error("", e);
            throw new UserDAOException(e);
        }
    }

    @Override
    protected User createObj() {
        return new User();
    }

    @Override
    protected User readObj(User obj, ResultSet source) throws UserDAOException {
        try {
            obj.setId(source.getLong(1));
            obj.setBitrixId(source.getLong(2));
            obj.setFirstName(source.getString(3));
            obj.setMiddleName(source.getString(4));
            obj.setLastName(source.getString(5));
            obj.setEmail(source.getString(6));
            obj.setPhone(source.getString(7));
            obj.setBirthDate(source.getDate(8));
            obj.setUserLogin(source.getString(9));
            obj.setUserPassword(source.getString(10));
        } catch (SQLException e) {
            logger.error("", e);
            throw new UserDAOException(e);
        }
        return obj;
    }
}
