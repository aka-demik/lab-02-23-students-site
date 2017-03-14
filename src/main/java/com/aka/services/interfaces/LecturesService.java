package com.aka.services.interfaces;

import com.aka.services.SuperUserServiceImpl;
import com.aka.services.exceptions.ServiceException;

import java.util.Collection;

public interface LecturesService {
    void deleteScheduledCall(long id) throws ServiceException;

    Collection<SuperUserServiceImpl.Lecture> getLectures() throws ServiceException;
}
