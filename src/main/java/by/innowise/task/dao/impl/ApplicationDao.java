package by.innowise.task.dao.impl;

import by.innowise.task.connection.ConnectionPool;
import by.innowise.task.dao.BaseDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.model.ApplicationModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao implements BaseDao<ApplicationModel> {
    private static final Logger logger = LogManager.getLogger();

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String FIND_BY_USER_ID_QUERY = "SELECT id, user_id, faculty_name, diploma_score, description, mobile_phone, fio, status FROM application WHERE user_id = ?;";
    private static final String INSERT_QUERY = "INSERT INTO application (user_id, faculty_name, diploma_score, description, mobile_phone, fio, status) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL_QUERY = "SELECT id, user_id, faculty_name, diploma_score, description, mobile_phone, fio, status FROM application;";

    public ApplicationModel findByUserId(long userId) throws DaoException {
        ApplicationModel applicationModel = null;
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID_QUERY);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                applicationModel = new ApplicationModel();
                applicationModel.setId(resultSet.getLong(1));
                applicationModel.setUserId(resultSet.getLong(2));
                applicationModel.setFacultyName(resultSet.getString(3));
                applicationModel.setDiplomaScore(resultSet.getLong(4));
                applicationModel.setDescription(resultSet.getString(5));
                applicationModel.setMobilePhone(resultSet.getString(6));
                applicationModel.setFio(resultSet.getString(7));
                applicationModel.setStatus(ApplicationModel.Status.valueOf(resultSet.getString(8)));
                logger.info("DAO: Application with userId {} was found", userId);
            } else {
                logger.info("DAO: Application with userId {} wasn't found", userId);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return applicationModel;
    }

    @Override
    public void insert(ApplicationModel applicationModel) throws DaoException {
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setLong(1, applicationModel.getUserId());
            preparedStatement.setString(2, applicationModel.getFacultyName());
            preparedStatement.setFloat(3, applicationModel.getDiplomaScore());
            preparedStatement.setString(4, applicationModel.getDescription());
            preparedStatement.setString(5, applicationModel.getMobilePhone());
            preparedStatement.setString(6, applicationModel.getFio());
            preparedStatement.setString(7, applicationModel.getStatus().toString());

            preparedStatement.executeUpdate();
            logger.info("DAO: User was successfully inserted");
        } catch (SQLException e) {
            logger.error("DAO: Failed to insert user " + e);
            throw new DaoException("Failed to insert user");
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<ApplicationModel> findAll() throws DaoException {
        List<ApplicationModel> applicationModels = new ArrayList<>();
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ApplicationModel applicationModel = new ApplicationModel();
                applicationModel.setId(resultSet.getLong(1));
                applicationModel.setUserId(resultSet.getLong(2));
                applicationModel.setFacultyName(resultSet.getString(3));
                applicationModel.setDiplomaScore(resultSet.getLong(4));
                applicationModel.setDescription(resultSet.getString(5));
                applicationModel.setMobilePhone(resultSet.getString(6));
                applicationModel.setFio(resultSet.getString(7));
                applicationModel.setStatus(ApplicationModel.Status.valueOf(resultSet.getString(8)));
                applicationModels.add(applicationModel);
            }
        } catch (SQLException e) {
            logger.error("DAO: Failed to get all applications " + e);
            throw new DaoException("Failed to get all applications");
        } finally {
            connectionPool.releaseConnection(connection);
        }

        return applicationModels;
    }

    @Override
    public ApplicationModel findById(long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void update(ApplicationModel applicationModel) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(ApplicationModel applicationModel) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
