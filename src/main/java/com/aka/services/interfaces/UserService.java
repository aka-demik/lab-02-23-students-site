package com.aka.services.interfaces;

import com.aka.dao.exceptions.PersistentException;
import com.aka.models.User;
import org.springframework.security.access.annotation.Secured;

import java.util.Collection;

public interface UserService {
    @Secured("ROLE_ADMIN")
    Collection<User> getUsers() throws PersistentException;
}
