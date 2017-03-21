package com.aka.services;

import com.aka.dao.UserEntityRepository;
import com.aka.entitys.UserEntity;
import com.aka.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private UserEntityRepository userDAO;

    @Autowired()
    public void setUserDAO(UserEntityRepository userDAO) {
        this.userDAO = userDAO;
    }

    public Collection<UserEntity> getUsers() {
        return null;
//        return userDAO.getAll();
    }

    public UserEntity authorizeUser(String mail, String pass) {
        UserEntity user = userDAO.findByMail(mail);
        if (user != null && user.getPassword().equals(pass)) {
            return user;
        } else {
            return null;
        }
    }
}
