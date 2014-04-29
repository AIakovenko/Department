package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Worker;
import com.bionic.iakovenko.department.dao.entity.Plan;
import com.bionic.iakovenko.department.dao.interfaces.IPlan;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import com.bionic.iakovenko.department.logger.SingleLogger;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 2:02 PM
 */
public class MySQLPlanDAO implements IPlan {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Selects from table Plan all notes.
     *
     * @return list of Plan objects.
     */
    @Override
    public List<Plan> findAll() {
        query = "SELECT * FROM Plan;";
        List<Plan> list = null;

        try {
            list = new ArrayList<Plan>();
            Plan plan;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                plan = createPlan();
                list.add(plan);
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
     * Selects from table Plan by worker id notes about request which worker must do or has done.
     * For implementation method must be taken worker ID value from Worker.
     * It can be done calling the method <code>getWorkerID()</> from Worker object.
     *
     * @param worker worker object;
     * @return list of request objects.
     */
    @Override
    public List<Request> findRequest(Worker worker) {
        query = "SELECT Request.* FROM Request, Plan WHERE Plan.Worker_ID = ? " +
                "AND Plan.Request_ID = Request.Request_ID;";
        List<Request> list = null;

        try {
            list = new ArrayList<Request>();
            Request request;
            preparedStatement = connect(query);
            preparedStatement.setShort(1, worker.getWorkerID());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                request = createRequest();
                list.add(request);
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
     * Selects from table Plan by request id notes about workers which must do or have done this request.
     * For implementation method must be taken request ID value from Request.
     * It can be done calling the method <code>getRequestID()</> from Request object.
     *
     * @param request Request object;
     * @return list of Worker objects.
     */
    @Override
    public List<Worker> findWorker(Request request) {
        query = "SELECT Worker.* FROM Worker, Plan WHERE Plan.Request_ID = ? " +
                "AND Plan.Worker_ID = Worker.Worker_ID;";
        List<Worker> list = null;

        try {
            list = new ArrayList<Worker>();
            Worker worker;
            preparedStatement = connect(query);
            preparedStatement.setInt(1, request.getRequestID());
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
     * Inserts note about plan to Plan table.
     *
     * @param request Request object,
     * @param worker  Worker object.
     * @return true     if note about plan has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertPlan(Request request, Worker worker) {
        query = "INSERT INTO Plan VALUES ((SELECT Request_ID FROM Request WHERE "
                + "Request_ID = ?),(SELECT Worker_ID FROM Worker WHERE Worker_ID = ?));";
        try {
            preparedStatement = connect(query);
            preparedStatement.setInt(1, request.getRequestID());
            preparedStatement.setShort(2, worker.getWorkerID());
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
     * Removes note about Plan from Plan table.
     *
     * @param request removed Request object,
     * @param worker  removed Worker object.
     * @return true     if note about working plan has been removed.
     * false    if the exception has thrown.
     */
    @Override
    public boolean deletePlan(Request request, Worker worker) {
        query = "DELETE FROM Plan WHERE Request_ID = ? AND Worker_ID = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setInt(1, request.getRequestID());
            preparedStatement.setShort(2, worker.getWorkerID());
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
     * Removes note about Plan from Plan table by request id.
     *
     * @param request removed Request object,
     * @return true     number of rows which have been removed.
     * if no one row has been removed then would be
     * returned 0.
     */
    @Override
    public int deletePlanByRequest(Request request) {
        query = "DELETE FROM Plan WHERE Request_ID = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setInt(1, request.getRequestID());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            log.warn(e.toString(), e);
        } finally {
            disconnect();
        }
        return 0;
    }

    /**
     * Removes note about Plan from Plan table by worker id number.
     *
     * @param worker removed Worker object.
     * @return true     number of rows which have been removed.
     * if no one row has been removed then would be
     * returned 0.
     */
    @Override
    public int deletePlanByWorker(Worker worker) {
        query = "DELETE FROM Plan WHERE Worker_ID = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setInt(1, worker.getWorkerID());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            log.warn(e.toString(), e);
        } finally {
            disconnect();
        }
        return 0;
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

    private Plan createPlan() throws SQLException {
        int requestID = resultSet.getInt(1);
        short workerID = resultSet.getShort(2);
        return new Plan(requestID, workerID);
    }

    private Request createRequest() throws SQLException {
        int requestID = resultSet.getInt(1);
        String personID = resultSet.getString(2);
        short flatID = resultSet.getShort(3);
        short worksID = resultSet.getShort(4);
        Date requestedTime = resultSet.getDate(5);
        short dispatcherID = resultSet.getShort(6);
        return new Request(requestID, personID, flatID, worksID, requestedTime, dispatcherID);
    }

    private Worker createWorker() throws SQLException {
        short workerID = resultSet.getShort(1);
        String name = resultSet.getString(2);
        String specialization = resultSet.getString(3);
        return new Worker(workerID, name, specialization);
    }

}
