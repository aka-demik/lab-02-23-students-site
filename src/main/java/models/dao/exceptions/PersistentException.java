package models.dao.exceptions;

public class PersistentException extends Exception {

    public PersistentException(Exception cause) {
        super(cause);
    }

}
