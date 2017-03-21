package com.aka.services;

import com.aka.dao.UserEntityRepository;
import com.aka.entitys.UserEntity;
import com.aka.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserEntityRepository userDAO;

    @Autowired()
    public void setUserDAO(UserEntityRepository userDAO) {
        this.userDAO = userDAO;
    }

    public Iterable<UserEntity> getUsers() {
        return userDAO.findAll();
    }

    public UserEntity authorizeUser(String mail, String pass) {
        UserEntity user = userDAO.findByMail(mail);
        if (user != null && user.getPassword().equals(pass)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public void registerUser(String login, String name, String password) {
        UserEntity user = new UserEntity();
        user.setMail(login);
        user.setName(name);
        user.setPassword(password);
        userDAO.save(user);
    }
}
