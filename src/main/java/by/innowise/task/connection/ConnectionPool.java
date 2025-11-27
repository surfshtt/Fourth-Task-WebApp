package by.innowise.task.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectionPool {

    private static final String URL = "jdbc:mysql://localhost:3306/web_project";
    private static final String USER = "root";
    private static final String PASSWORD = "123412341234";

    private static final int POOL_SIZE = 10;

    private final Queue<Connection> availableConnections = new ArrayDeque<>();

    private static ConnectionPool instance;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ConnectionPool() throws SQLException {
        for (int i = 0; i < POOL_SIZE; i++) {
            availableConnections.add(createConnection());
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public synchronized Connection getConnection() throws SQLException {
        while (availableConnections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return availableConnections.poll();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            availableConnections.offer(connection);
            notifyAll();
        }
    }

    public synchronized void shutdown() throws SQLException {
        for (Connection conn : availableConnections) {
            conn.close();
        }
        availableConnections.clear();
    }
}
