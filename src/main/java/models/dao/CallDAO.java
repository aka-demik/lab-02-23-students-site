package models.dao;

import models.dao.exceptions.CallDAOException;
import models.pojo.Call;
import org.apache.log4j.Logger;

import java.sql.*;

public class CallDAO extends AbstractDAO<Call> {
    private static Logger logger = Logger.getLogger(CallDAO.class);

    public CallDAO(Connection con) {
        super(con);
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT " +
                "id, call_reason_id, user_id, superuser_id, created_at, status " +
                "FROM call";
    }

    @Override
    protected String getSelectOneSQL() {
        return "SELECT " +
                "id, call_reason_id, user_id, superuser_id, created_at, status " +
                "FROM call WHERE id = ?";
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO call " +
                "(id, call_reason_id, user_id, superuser_id, created_at, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE call SET " +
                "call_reason_id = ?, user_id = ?, superuser_id = ?, created_at = ?, status = ? " +
                "WHERE id = ?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM call WHERE id = ?";
    }

    @Override
    protected String getDeleteAllSQL() {
        return "DELETE FROM call";
    }

    @Override
    protected void prepareInsert(Call obj, PreparedStatement statement) throws CallDAOException {
        try {
            statement.setLong(1, obj.getId());
            statement.setLong(2, obj.getCallReasonId());
            statement.setLong(3, obj.getUserId());
            statement.setLong(4, obj.getSuperuserId());
            statement.setTimestamp(5, new Timestamp(obj.getCreatedAt().getTime()));
            statement.setShort(6, obj.getStatus());
        } catch (SQLException e) {
            logger.error("", e);
            throw new CallDAOException(e);
        }
    }

    @Override
    protected void prepareUpdate(Call obj, PreparedStatement statement) throws CallDAOException {
        try {
            statement.setLong(1, obj.getCallReasonId());
            statement.setLong(2, obj.getUserId());
            statement.setLong(3, obj.getSuperuserId());
            statement.setTimestamp(4, new Timestamp(obj.getCreatedAt().getTime()));
            statement.setShort(5, obj.getStatus());
            statement.setLong(6, obj.getId());
        } catch (SQLException e) {
            logger.error("", e);
            throw new CallDAOException(e);
        }
    }

    @Override
    protected Call createObj() {
        return new Call();
    }

    @Override
    protected Call readObj(Call obj, ResultSet source) throws CallDAOException {
        try {
            obj.setId(source.getLong(1));
            obj.setCallReasonId(source.getLong(2));
            obj.setUserId(source.getLong(3));
            obj.setSuperuserId(source.getLong(4));
            obj.setCreatedAt(source.getTimestamp(5));
            obj.setStatus(source.getShort(6));
        } catch (SQLException e) {
            logger.error("", e);
            throw new CallDAOException(e);
        }
        return obj;
    }
}
