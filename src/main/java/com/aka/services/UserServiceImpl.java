package com.aka.services;

import com.aka.dao.UserDAO;
import com.aka.dao.exceptions.PersistentException;
import com.aka.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired()
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Collection<User> getUsers() throws PersistentException {
        return userDAO.getAll();
    }
}
