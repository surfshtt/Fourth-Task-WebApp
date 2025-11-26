package by.innowise.task.dao.impl;

import by.innowise.task.connection.ConnectionManager;
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

    @Override
    public UserModel findById(int id) throws DaoException {
        UserModel user = null;

        final String query = "SELECT * FROM user WHERE id = ? LIMIT 1;";

        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        }

        return user;
    }

    @Override
    public List<UserModel> findAll() throws DaoException {
        List<UserModel> users = new ArrayList<>();

        final String query = "SELECT * FROM user;";

        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        }

        return users;
    }

    @Override
    public void insert(UserModel user) throws DaoException {
        final String query = "INSERT INTO user (name, password, email, role) VALUES (?, ?, ?, ?);";

        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole().toString());

            preparedStatement.executeUpdate();
            logger.info("DAO: User {} was successfully inserted", user.getId());
        } catch (SQLException e){
            logger.error("DAO: Failed to insert user {}", user.getId());
            throw new DaoException("Failed to insert user");
        }
    }

    @Override
    public void update(UserModel user) throws DaoException {
        final String query = "UPDATE user SET name = ?, password = ?, email = ?, role = ? WHERE id = ?";

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

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
        }
    }

    @Override
    public void delete(UserModel user) throws DaoException {
        final String query = "DELETE FROM user WHERE id = ?";

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DaoException("User not found with id: " + user.getId());
            }

            logger.info("DAO: User with id {} was successfully deleted", user.getId());
        } catch (SQLException e) {
            logger.error("DAO: Failed to delete user {}", user.getId());
            throw new DaoException("Failed to update user");
        }
    }
}
