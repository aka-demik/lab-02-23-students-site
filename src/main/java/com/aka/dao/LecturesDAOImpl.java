package com.aka.dao;

import com.aka.dao.exceptions.PersistentException;
import com.aka.dao.exceptions.ScheduledCallDAOException;
import com.aka.models.ScheduledLecture;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Repository
public class LecturesDAOImpl extends AbstractDAO<ScheduledLecture> implements LecturesDAO {
    private static Logger logger = Logger.getLogger(LecturesDAOImpl.class);
    private LectureDAO lectureDAO;

    @Autowired
    public void setLectureDAO(LectureDAO lectureDAO) {
        this.lectureDAO = lectureDAO;
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
    protected void prepareInsert(ScheduledLecture obj, PreparedStatement statement) throws ScheduledCallDAOException {
        try {
            statement.setLong(1, obj.getId());
            if (obj.getLecture() != null)
                statement.setLong(2, obj.getLecture().getId());
            statement.setTimestamp(3, new Timestamp(obj.getScheduledAt().getTime()));
        } catch (SQLException e) {
            logger.error("", e);
            throw new ScheduledCallDAOException(e);
        }
    }

    @Override
    protected void prepareUpdate(ScheduledLecture obj, PreparedStatement statement) throws ScheduledCallDAOException {
        try {
            if (obj.getLecture() != null)
                statement.setLong(1, obj.getLecture().getId());
            statement.setTimestamp(2, new Timestamp(obj.getScheduledAt().getTime()));
            statement.setLong(3, obj.getId());
        } catch (SQLException e) {
            logger.error("", e);
            throw new ScheduledCallDAOException(e);
        }
    }

    @Override
    protected ScheduledLecture createObj() {
        return new ScheduledLecture();
    }

    @Override
    protected ScheduledLecture readObj(ScheduledLecture obj, ResultSet source) throws ScheduledCallDAOException {
        try {
            obj.setId(source.getLong(1));
            obj.setLecture(lectureDAO.getByID(source.getLong(2)));
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
