package controllers.listeners;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static Logger logger = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.trace(se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.trace(se.getSession().getId());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.trace("add " + event.getName() + " - " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.trace("del " + event.getName() + " - " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.trace("rep " + event.getName() + " - " + event.getValue());
    }
}
