package com.aka.dao;

import com.aka.dao.exceptions.PersistentException;
import com.aka.models.CallReason;

import java.util.Collection;

public interface CallReasonDAO {
    Collection<CallReason> getAll() throws PersistentException;

    CallReason getByID(long callReasonId) throws PersistentException;
}
