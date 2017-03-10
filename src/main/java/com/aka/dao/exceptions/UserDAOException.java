package com.aka.dao.exceptions;

public class UserDAOException extends PersistentException {

    public UserDAOException(Exception cause) {
        super(cause);
    }
}
