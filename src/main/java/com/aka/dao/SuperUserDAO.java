package com.aka.dao;

import com.aka.dao.exceptions.PersistentException;
import com.aka.dao.exceptions.SuperUserDAOException;
import com.aka.models.SuperUser;

public interface SuperUserDAO {
    boolean insert(SuperUser obj) throws PersistentException;

    boolean deleteByID(long id) throws PersistentException;

    SuperUser getByLogin(String login) throws SuperUserDAOException;
}
