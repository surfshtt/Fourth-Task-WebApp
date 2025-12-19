package by.innowise.task.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();

    private static final String URL = "jdbc:mysql://localhost:3306/web_project";
    private static final String USER = "root";
    private static final String PASSWORD = "123412341234";

    private static final int POOL_SIZE = 10;

    private final Queue<Connection> availableConnections = new ArrayDeque<>();

    private static ConnectionPool instance;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            LogManager.getLogger().info("CONNECTION_POOL: JDBC driver successfully loaded");
        } catch (ClassNotFoundException e) {
            LogManager.getLogger().error("CONNECTION_POOL: Failed to load JDBC driver", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                availableConnections.add(connection);
            }
            catch (SQLException e) {
                logger.error("CONNECTION_POOL: Failed to create connection {}", i + 1, e);
                throw new ExceptionInInitializerError(e);
            }
        }
        logger.info("CONNECTION_POOL: Initialized with {} connections", POOL_SIZE);
    }

    public synchronized Connection getConnection() throws SQLException {
        while (availableConnections.isEmpty()) {
            try {
                logger.warn("CONNECTION_POOL: No available connections, waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("CONNECTION_POOL: Waiting for connection was interrupted", e);
            }
        }

        Connection connection = availableConnections.poll();
        logger.debug("CONNECTION_POOL: Connection acquired, available connections: {}", availableConnections.size());
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            availableConnections.offer(connection);
            notifyAll();
            logger.debug("CONNECTION_POOL: Connection released, available connections: {}", availableConnections.size());
        } else {
            logger.warn("CONNECTION_POOL: Attempted to release null connection");
        }
    }

    public synchronized void shutdown() throws SQLException {
        logger.info("CONNECTION_POOL: Shutting down, closing {} connections", availableConnections.size());
        for (Connection conn : availableConnections) {
            conn.close();
        }
        availableConnections.clear();
        logger.info("CONNECTION_POOL: Shutdown complete");
    }
}
