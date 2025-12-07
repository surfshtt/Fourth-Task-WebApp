package by.innowise.task.dao.impl;

import by.innowise.task.connection.ConnectionPool;
import by.innowise.task.dao.BaseDao;
import by.innowise.task.exception.DaoException;
import by.innowise.task.model.ApplicationModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ApplicationDao implements BaseDao<ApplicationModel> {
    private static final Logger logger = LogManager.getLogger();

    private final ConnectionPool connectionPool;

    private static final String INSERT_QUERY = "INSERT INTO application (user_id, faculty_name, diploma_score, description, mobile_phone, fio, status) VALUES (?, ?, ?, ?, ?, ?, ?);";


    public ApplicationDao() {
        try {
            connectionPool = ConnectionPool.getInstance();
        }
        catch (Exception e) {
            throw new ExceptionInInitializerError("Cannot get pool's instance");
        }
    }

    @Override
    public void insert(ApplicationModel applicationModel) throws DaoException {
        Connection connection = null;

        try{
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
        } catch (SQLException e){
            logger.error("DAO: Failed to insert user " + e);
            throw new DaoException("Failed to insert user");
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public ApplicationModel findById(int id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<ApplicationModel> findAll() throws DaoException {
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
