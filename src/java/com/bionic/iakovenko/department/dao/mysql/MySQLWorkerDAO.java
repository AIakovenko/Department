package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Worker;
import com.bionic.iakovenko.department.dao.interfaces.IWorker;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import com.bionic.iakovenko.department.logger.SingleLogger;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 2:00 PM
 */
public class MySQLWorkerDAO implements IWorker {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Selects from table Worker all notes.
     *
     * @return list of Workers.
     */
    @Override
    public List<Worker> findAll() {

        query = "SELECT * FROM Worker;";
        List<Worker> list = null;

        try {
            list = new ArrayList<Worker>();
            Worker worker;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery(query);

            while (resultSet.next()) {

                worker = createWorker();
                list.add(worker);
            }

        } catch (SQLException e) {
            log.warn(e.toString(), e);
            return null;
        } finally {
            disconnect();
        }

        return list;
    }

    /**
     * Selects from table Worker one note by id.
     *
     * @param workerId worker id number;
     * @return Worker object.
     */
    @Override
    public Worker findWorker(short workerId) {
        query = "SELECT * FROM Worker WHERE Worker_ID = ?;";
        Worker worker = null;

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, workerId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            worker = createWorker();
        } catch (SQLException e) {
            log.warn(e.toString(), e);
            return null;
        } finally {
            disconnect();
        }
        return worker;
    }

    /**
     * Selects from table Worker one note by name.
     *
     * @param name worker's name;
     * @return Worker object.
     */
    @Override
    public List<Worker> findWorker(String name) {
        query = "SELECT * FROM Worker WHERE Name = ?;";
        List<Worker> list = null;

        try {
            list = new ArrayList<Worker>();
            Worker worker;
            preparedStatement = connect(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                worker = createWorker();
                list.add(worker);
            }
        } catch (SQLException e) {
            log.warn(e.toString(), e);
            return null;
        } finally {
            disconnect();
        }

        return list;
    }

    /**
     * Inserts note about Worker to Worker table.
     *
     * @param worker
     * @return true     if note about Worker has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertWorker(Worker worker) {
        query = "INSERT INTO Worker VALUES (?, ?, ?);";

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, worker.getWorkerID());
            preparedStatement.setString(2, worker.getName());
            preparedStatement.setString(3, worker.getSpecialization());

            int result = preparedStatement.executeUpdate();
            return result == 1 ? true : false;
        } catch (SQLException e) {
            log.warn(e.toString(), e);
        } finally {
            disconnect();
        }
        return false;
    }

    /**
     * Removes note about worker to Worker table.
     *
     * @param worker worker who would be removed.
     * @return true     if note about Worker has been removed.
     * false    if the exception has thrown.
     */
    @Override
    public boolean deleteWorker(Worker worker) {
        String query = "DELETE FROM Worker WHERE Worker_ID = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, worker.getWorkerID());

            int result = preparedStatement.executeUpdate();
            return result == 1 ? true : false;

        } catch (SQLException e) {
            log.warn(e.toString(), e);
        } finally {
            disconnect();
        }
        return false;
    }

    /*
    * Provides connection to data base.
    */
    private PreparedStatement connect(String query) throws SQLException {
        source = ConnectionManager.getInstance();
        connection = source.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }

    private void disconnect() {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                log.warn(ex.toString(), ex);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                log.warn(ex.toString(), ex);
            }
        }
        if (connection != null) {
            source.freeConnection(connection);
        }
    }

    private Worker createWorker() throws SQLException {
        short workerID = resultSet.getShort(1);
        String name = resultSet.getString(2);
        String specialization = resultSet.getString(3);
        return new Worker(workerID, name, specialization);
    }
}
