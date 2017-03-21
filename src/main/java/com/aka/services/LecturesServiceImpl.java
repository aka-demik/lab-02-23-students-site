package com.aka.services;

import com.aka.dao.LecturesDAO;
import com.aka.services.exceptions.LecturesServiceException;
import com.aka.services.interfaces.LecturesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LecturesServiceImpl implements LecturesService {
    private static Logger logger = Logger.getLogger(LecturesServiceImpl.class);
    private LecturesDAO lecturesDAO;

    @Autowired
    public void setLecturesDAO(LecturesDAO lecturesDAO) {
        this.lecturesDAO = lecturesDAO;
    }

    @Override
    public void deleteScheduledCall(long id) throws LecturesServiceException {
    }

    @Override
    public Collection<SuperUserServiceImpl.Lecture> getLectures() throws LecturesServiceException {
        return null;
    }
}
