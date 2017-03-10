package com.aka.services;

import com.aka.dao.exceptions.PersistentException;
import com.aka.models.User;

import java.util.Collection;

public interface UserService {
    Collection<User> getUsers() throws PersistentException;
}
