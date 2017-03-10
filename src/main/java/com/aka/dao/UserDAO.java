package com.aka.dao;

import com.aka.dao.exceptions.PersistentException;
import com.aka.models.User;

import java.util.Collection;

public interface UserDAO {
    Collection<User> getAll() throws PersistentException;
}
