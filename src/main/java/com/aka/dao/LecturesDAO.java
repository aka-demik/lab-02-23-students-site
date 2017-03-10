package com.aka.dao;

import com.aka.dao.exceptions.PersistentException;
import com.aka.models.ScheduledLecture;

import java.util.Collection;

public interface LecturesDAO {
    Collection<ScheduledLecture> getAll() throws PersistentException;

    ScheduledLecture getByID(long id) throws PersistentException;
}
