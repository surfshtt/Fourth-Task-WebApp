package by.innowise.task.dao.impl;

import by.innowise.task.connection.ConnectionPool;
import by.innowise.task.dao.BaseDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.model.user.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements BaseDao<UserModel> {
    private static final Logger logger = LogManager.getLogger();

    private final ConnectionPool connectionPool;

    private static final String FIND_BY_ID_QUERY = "SELECT id, name, password, email, role FROM user WHERE id = ? LIMIT 1;";
    private static final String FIND_ALL_QUERY = "SELECT id, name, password, email, role FROM user;";
    private static final String INSERT_QUERY = "INSERT INTO user (name, password, email, role) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_BY_ID_QUERY = "UPDATE user SET name = ?, password = ?, email = ?, role = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM user WHERE id = ?";
    private static final String FIND_BY_NAME_QUERY = "SELECT id, name, password, email, role FROM user WHERE name = ? LIMIT 1;";

    public UserDao() {
        try {
            connectionPool = ConnectionPool.getInstance();
        }
        catch (Exception e) {
            throw new ExceptionInInitializerError("Cannot get pool's instance");
        }
    }

    @Override
    public UserModel findById(int id) throws DaoException {
        UserModel user = null;
        Connection connection = null;

        try{
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(UserModel.Role.valueOf(resultSet.getString("role")));
                logger.info("DAO: User with id {} was found", id);
            }
            else{
                logger.info("DAO: User with id {} wasn't found", id);
            }
        } catch (SQLException e){
            logger.error("DAO: Search of a user with id {} is failed", id);
            throw new DaoException("Failed to find a user with id " + id);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return user;
    }

    @Override
    public List<UserModel> findAll() throws DaoException {
        List<UserModel> users = new ArrayList<>();
        Connection connection = null;

        try{
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                UserModel user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(UserModel.Role.valueOf(resultSet.getString("role")));
                users.add(user);
            }
        } catch (SQLException e){
            logger.error("DAO: Failed to find all users");
            throw new DaoException("Failed to find users");
        }finally{
            connectionPool.releaseConnection(connection);
        }

        return users;
    }

    @Override
    public void insert(UserModel user) throws DaoException {
        Connection connection = null;

        try{
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole().toString());

            preparedStatement.executeUpdate();
            logger.info("DAO: User was successfully inserted");
        } catch (SQLException e){
            logger.error("DAO: Failed to insert user " + e);
            throw new DaoException("Failed to insert user");
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(UserModel user) throws DaoException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_QUERY);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole().name());
            preparedStatement.setLong(5, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                logger.info("DAO: User {} wasn't updated", user.getId());
                throw new DaoException("User not found with id: " + user.getId());
            }
        } catch (SQLException e) {
            logger.error("DAO: Failed to update user {}", user.getId());
            throw new DaoException("Failed to update user");
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(UserModel user) throws DaoException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            preparedStatement.setLong(1, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DaoException("User not found with id: " + user.getId());
            }

            logger.info("DAO: User with id {} was successfully deleted", user.getId());
        } catch (SQLException e) {
            logger.error("DAO: Failed to delete user {}", user.getId());
            throw new DaoException("Failed to update user");
        } finally{
            connectionPool.releaseConnection(connection);
        }
    }

    public UserModel findByName(String name) throws DaoException {
        UserModel user = null;
        Connection connection = null;

        try{
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(UserModel.Role.valueOf(resultSet.getString("role")));
                logger.info("DAO: User with name {} was found", name);
            }
            else{
                logger.info("DAO: User with name {} wasn't found", name);
            }
        } catch (SQLException e){
            logger.error("DAO: Search o user is failed: " + e);
            throw new DaoException("Failed to find a user with name " + name);
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return user;
    }
}
