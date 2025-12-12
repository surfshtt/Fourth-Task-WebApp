package by.innowise.task.listener;

import by.innowise.task.connection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try{
            ConnectionPool.getInstance();
            logger.info("LISTENER: Connection pool initialized");
        } catch (Exception e) {
            logger.warn("LISTENER: Failed to initialize connection pool", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().shutdown();
            logger.info("LISTENER: Connection pool shutdown complete");
        }catch (Exception e) {
            logger.warn("LISTENER: Failed to shutdown connection pool", e);
            throw new ExceptionInInitializerError(e);
        }
    }
}
