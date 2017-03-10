package com.aka.dao;

import com.aka.dao.exceptions.CallReasonDAOException;
import com.aka.models.CallReason;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CallReasonDAOImpl extends AbstractDAO<CallReason> implements CallReasonDAO {
    private static Logger logger = Logger.getLogger(CallReasonDAOImpl.class);

    @Override
    protected String getSelectAllSQL() {
        return "SELECT " +
                "id, name, script " +
                "FROM call_reason";
    }

    @Override
    protected String getSelectOneSQL() {
        return "SELECT " +
                "id, name, script " +
                "FROM call_reason " +
                "WHERE id = ?";
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO call_reason " +
                "(id, name, script) " +
                "VALUES (?, ?, ?)";
    }

    @Override
    protected String getUpdateSQL() {
        return "UPDATE call_reason SET " +
                "name = ?, script = ? " +
                "WHERE id = ?";
    }

    @Override
    protected String getDeleteSQL() {
        return "DELETE FROM call_reason WHERE id = ?";
    }

    @Override
    protected String getDeleteAllSQL() {
        return "DELETE FROM call_reason";
    }

    @Override
    protected void prepareInsert(CallReason obj, PreparedStatement statement) throws CallReasonDAOException {
        try {
            statement.setLong(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setString(3, obj.getScript());
        } catch (SQLException e) {
            logger.error("", e);
            throw new CallReasonDAOException(e);
        }
    }

    @Override
    protected void prepareUpdate(CallReason obj, PreparedStatement statement) throws CallReasonDAOException {
        try {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getScript());
            statement.setLong(3, obj.getId());
        } catch (SQLException e) {
            logger.error("", e);
            throw new CallReasonDAOException(e);
        }
    }

    @Override
    protected CallReason createObj() {
        return new CallReason();
    }

    @Override
    protected CallReason readObj(CallReason obj, ResultSet source) throws CallReasonDAOException {
        try {
            obj.setId(source.getLong(1));
            obj.setName(source.getString(2));
            obj.setScript(source.getString(3));
        } catch (SQLException e) {
            logger.error("", e);
            throw new CallReasonDAOException(e);
        }
        return obj;
    }
}
