package models.dao;

import models.pojo.User;
import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

public class UserDataObject extends AbstractDataObject<User> {
    Logger logger = Logger.getLogger(UserDataObject.class);

    public UserDataObject(Connection con) {
        super(con);
    }

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
    protected void prepareInsert(User obj, PreparedStatement statement) throws SQLException {
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
    }

    @Override
    protected void prepareUpdate(User obj, PreparedStatement statement) throws SQLException {
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
    }

    @Override
    protected User createObj() {
        return new User();
    }

    @Override
    protected User readObj(User obj, ResultSet source) throws SQLException {
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

        return obj;
    }

    public User getByLogin(String login, String pass) {
        try (PreparedStatement statement = con.prepareStatement("SELECT " +
                "id, bitrix_id, firstname, middlename, lastname, email, phone, " +
                "birthdate, user_login, user_password " +
                "FROM \"user\" " +
                "WHERE user_login = ? AND user_password = ?")) {
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
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
