package com.aka.services;

import com.aka.dao.UserDAO;
import com.aka.dao.exceptions.PersistentException;
import com.aka.models.User;
import com.aka.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired()
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Secured("ROLE_ADMIN")
    public Collection<User> getUsers() throws PersistentException {
        return userDAO.getAll();
    }
}
