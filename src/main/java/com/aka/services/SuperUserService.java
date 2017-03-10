package com.aka.services;

import com.aka.dao.exceptions.PersistentException;

import java.util.Collection;

public interface SuperUserService {
    boolean authorize(String login, String password) throws PersistentException;

    boolean registerUser(String login, String password) throws PersistentException;

    void deleteScheduledCall(long id) throws PersistentException;

    Collection<SuperUserServiceImpl.Lecture> getLectures() throws PersistentException;

    SuperUserServiceImpl.Lecture getLectureByID(long id) throws PersistentException;
}
