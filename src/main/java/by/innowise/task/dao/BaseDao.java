package by.innowise.task.dao;

import by.innowise.task.exception.DaoException;

import java.util.List;

public interface BaseDao<T> {
    T findById(int id) throws DaoException;
    List<T> findAll() throws DaoException;
    void insert(T t) throws DaoException;
    void update(T t) throws DaoException;
    void delete(T t) throws DaoException;
}
