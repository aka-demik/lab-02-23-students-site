package com.aka.dao;

import com.aka.dao.exceptions.PersistentException;
import com.aka.models.Lecture;

public interface LectureDAO {
    Lecture getByID(long aLong) throws PersistentException;
}
