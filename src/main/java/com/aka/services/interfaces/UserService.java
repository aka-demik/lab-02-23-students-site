package com.aka.services.interfaces;

import com.aka.entitys.UserEntity;

import java.util.Collection;

public interface UserService {
    Collection<UserEntity> getUsers();
    UserEntity authorizeUser(String mail, String pass);
}
