package controllers.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationLoadListener implements ServletContextListener {
    private static Logger logger = Logger.getLogger(ApplicationLoadListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.trace("site started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.trace("site stoped");
    }
}
