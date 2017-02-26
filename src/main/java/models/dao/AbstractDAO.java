package models.dao;

import models.dao.exceptions.PersistentException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractDAO<T> {
    private static Logger logger = Logger.getLogger(AbstractDAO.class);

    protected Connection con;

    public AbstractDAO(Connection con) {
        this.con = con;
    }

    protected abstract String getSelectAllSQL();

    protected abstract String getSelectOneSQL();

    protected abstract String getInsertSQL();

    protected abstract String getUpdateSQL();

    protected abstract String getDeleteSQL();

    protected abstract String getDeleteAllSQL();

    protected abstract void prepareInsert(T obj, PreparedStatement statement) throws PersistentException;

    protected abstract void prepareUpdate(T obj, PreparedStatement statement) throws PersistentException;

    protected abstract T createObj();

    protected abstract T readObj(T obj, ResultSet source) throws PersistentException;

    public T getByID(long id) throws PersistentException {
        try (PreparedStatement statement = con.prepareStatement(getSelectOneSQL())) {
            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return readObj(createObj(), result);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("", e);
            throw new PersistentException(e);
        }
    }

    public void getAll(Collection<T> dest) throws PersistentException {
        try (Statement statement = con.createStatement()) {
            try (ResultSet result = statement.executeQuery(getSelectAllSQL())) {
                while (result.next()) {
                    dest.add(readObj(createObj(), result));
                }
            }
        } catch (SQLException e) {
            logger.error("", e);
            throw new PersistentException(e);
        }
    }

    public Collection<T> getAll() throws PersistentException {
        Collection<T> tmp = new ArrayList<>();
        getAll(tmp);
        return tmp;
    }

    public boolean save(T obj) throws PersistentException {
        try (PreparedStatement statement = con.prepareStatement(getUpdateSQL())) {
            prepareUpdate(obj, statement);
            return statement.execute();
        } catch (SQLException e) {
            logger.error("", e);
            throw new PersistentException(e);
        }
    }

    public void save(Collection<? extends T> objs) throws PersistentException {
        try (PreparedStatement statement = con.prepareStatement(getUpdateSQL())) {
            for (T obj : objs) {
                prepareUpdate(obj, statement);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            logger.error("", e);
            throw new PersistentException(e);
        }
    }

    public boolean insert(T obj) throws PersistentException {
        try (PreparedStatement statement = con.prepareStatement(getInsertSQL())) {
            prepareInsert(obj, statement);
            return statement.execute();
        } catch (SQLException e) {
            logger.error("", e);
            throw new PersistentException(e);
        }
    }

    public void insert(Collection<? extends T> objs) throws PersistentException {
        try (PreparedStatement statement = con.prepareStatement(getInsertSQL())) {
            for (T obj : objs) {
                prepareInsert(obj, statement);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            logger.error("", e);
            throw new PersistentException(e);
        }
    }

    public boolean deleteByID(long id) throws PersistentException {
        try (PreparedStatement statement = con.prepareStatement(getDeleteSQL())) {
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            logger.error("", e);
            throw new PersistentException(e);
        }
    }

    public boolean deleteAll() throws PersistentException {
        try (Statement statement = con.createStatement()) {
            return statement.execute(getDeleteAllSQL());
        } catch (SQLException e) {
            logger.error("", e);
            throw new PersistentException(e);
        }
    }
}
