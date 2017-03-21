package com.aka.services.interfaces;

import com.aka.entitys.UserEntity;

public interface UserService {
    Iterable<UserEntity> getUsers();

    UserEntity authorizeUser(String mail, String pass);

    void registerUser(String login, String name, String password);
}
