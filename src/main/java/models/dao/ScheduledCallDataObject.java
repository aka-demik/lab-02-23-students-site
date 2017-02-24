package models.dao;

import models.ScheduledCall;
import models.dao.exceptions.PersistentException;
import models.dao.exceptions.ScheduledCallDAOException;
import org.apache.log4j.Logger;

import java.sql.*;

public class ScheduledCallDataObject extends AbstractDAO<ScheduledCall> {
    private static Logger logger = Logger.getLogger(ScheduledCallDataObject.class);

    private CallDAO callDataObject;

    public ScheduledCallDataObject(Connection con) {
        super(con);
        callDataObject = new CallDAO(con);
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT id, call_id, scheduled_at FROM scheduled_call";
    }

    @Override
    protected String getSelectOneSQL() {
        return "SELECT id, call_id, scheduled_at FROM scheduled_call WHERE id = ?";
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO scheduled_call " +
                "(id, call_id, scheduled_at) " +
                "VALUES (?, ?, ?)";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE scheduled_call SET " +
                "call_id = ?, scheduled_at = ? " +
                "WHERE id = ?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM scheduled_call WHERE id = ?";
    }

    @Override
    protected String getDeleteAllSQL() {
        return "DELETE FROM scheduled_call";
    }

    @Override
    protected void prepareInsert(ScheduledCall obj, PreparedStatement statement) throws ScheduledCallDAOException {
        try {
            statement.setLong(1, obj.getId());
            if (obj.getCall() != null)
                statement.setLong(2, obj.getCall().getId());
            statement.setTimestamp(3, new Timestamp(obj.getScheduledAt().getTime()));
        } catch (SQLException e) {
            logger.error("", e);
            throw new ScheduledCallDAOException(e);
        }
    }

    @Override
    protected void prepareUpdate(ScheduledCall obj, PreparedStatement statement) throws ScheduledCallDAOException {
        try {
            if (obj.getCall() != null)
                statement.setLong(1, obj.getCall().getId());
            statement.setTimestamp(2, new Timestamp(obj.getScheduledAt().getTime()));
            statement.setLong(3, obj.getId());
        } catch (SQLException e) {
            logger.error("", e);
            throw new ScheduledCallDAOException(e);
        }
    }

    @Override
    protected ScheduledCall createObj() {
        return new ScheduledCall();
    }

    @Override
    protected ScheduledCall readObj(ScheduledCall obj, ResultSet source) throws ScheduledCallDAOException {
        try {
            obj.setId(source.getLong(1));
            obj.setCall(callDataObject.getByID(source.getLong(2)));
            obj.setScheduledAt(source.getTimestamp(3));
        } catch (SQLException e) {
            logger.error("", e);
            throw new ScheduledCallDAOException(e);
        } catch (PersistentException e) {
            throw new ScheduledCallDAOException(e);
        }

        return obj;
    }
}
