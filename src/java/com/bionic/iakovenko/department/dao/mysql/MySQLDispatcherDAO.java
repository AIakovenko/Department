package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Dispatcher;
import com.bionic.iakovenko.department.dao.interfaces.IDispatcher;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import com.bionic.iakovenko.department.logger.SingleLogger;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 1:57 PM
 */
public class MySQLDispatcherDAO implements IDispatcher {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Looking for all notes into table Dispatcher.
     *
     * @return list of Dispatchers.
     */
    @Override
    public List<Dispatcher> findAll() {
        query = "SELECT * FROM Dispatcher;";
        List<Dispatcher> list = null;


        try {
            list = new ArrayList<Dispatcher>();
            Dispatcher dispatcher;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dispatcher = createDispatcher();
                list.add(dispatcher);
            }
        } catch (SQLException e) {
            log.warn(e.toString());
            return null;
        } finally {
            disconnect();
        }
        return list;
    }

    /**
     * Looking for one note by id into table Dispatcher.
     *
     * @param dispatcherID dispatcher id number;
     * @return Dispatcher object.
     */
    @Override
    public Dispatcher findDispatcher(short dispatcherID) {
        query = "SELECT * FROM Dispatcher WHERE Dispatcher_ID = ?;";
        Dispatcher dispatcher = null;
        try {
            preparedStatement = connect(query);
            preparedStatement.setInt(1, dispatcherID);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            dispatcher = createDispatcher();

        } catch (SQLException e) {
            log.warn(e.toString(), e);
            return null;
        } finally {
            disconnect();
        }
        return dispatcher;
    }

    /**
     * Looking for dispatchers by name into table Dispatcher.
     *
     * @param name dispatcher's name;
     * @return Dispatchers.
     */
    @Override
    public List<Dispatcher> findDispatchers(String name) {
        query = "SELECT * FROM Dispatcher WHERE NAME = ?;";
        List<Dispatcher> list = null;
        try {
            list = new ArrayList<Dispatcher>();
            Dispatcher dispatcher;
            preparedStatement = connect(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dispatcher = createDispatcher();
                list.add(dispatcher);
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
     * Looking for dispatchers by name into table Dispatcher.
     *
     * @param login dispatcher's login;
     * @return Dispatchers.
     */
    @Override
    public Dispatcher findDispatchersByLogin(String login) {
        query = "SELECT * FROM Dispatcher WHERE Login = ?;";
        Dispatcher dispatcher = null;
        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dispatcher = createDispatcher();
            } else {
                return null;
            }

        } catch (SQLException e) {
            log.warn(e.toString());
        } finally {
            disconnect();
        }
        return dispatcher;
    }

    /**
     * Adds note about Dispatcher to Dispatcher table.
     *
     * @param dispatcher
     * @return true     if note about dispatcher has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertDispatcher(Dispatcher dispatcher) {
        String query = "INSERT INTO Dispatcher VALUES (?,?,?);";
        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, dispatcher.getDispatcherID());
            preparedStatement.setString(2, dispatcher.getName());
            preparedStatement.setNull(3, Types.VARCHAR);
            int result = preparedStatement.executeUpdate();
            if (result == 1)
                return true;
        } catch (SQLException e) {
            log.warn(e.toString(), e);
        } finally {
            disconnect();
        }
        return false;

    }

    /**
     * Removes note about Dispatcher from Dispatcher table.
     *
     * @param dispatcher
     * @return true     if note about dispatcher has been removed.
     * false   if the exception has thrown.
     */
    @Override
    public boolean deleteDispatcher(Dispatcher dispatcher) {
        String query = "DELETE FROM Dispatcher WHERE Dispatcher_ID = ? AND Name = ?;";
        if (update(dispatcher, query)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Provides connection to data base.
     */
    private PreparedStatement connect(String query) throws SQLException {
        PreparedStatement preparedStatement;
        source = ConnectionManager.getInstance();
        connection = source.getConnection();
        preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }

    protected void disconnect() {

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

    /*
     *Execute update-like queries.
     * @param query must be contained ControllerId and Name parameters.
     */
    private boolean update(Dispatcher dispatcher, String query) {
        short id = dispatcher.getDispatcherID();
        String name = dispatcher.getName();

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, id);
            preparedStatement.setString(2, name);
            int result = preparedStatement.executeUpdate();
            if (result == 1)
                return true;
        } catch (SQLException e) {
            log.warn(e.toString(), e);
        } finally {
            disconnect();
        }
        return false;
    }

    private Dispatcher createDispatcher() throws SQLException {
        short dispatcherID = resultSet.getShort(1);
        String name = resultSet.getString(2);
        String login = resultSet.getString(3);
        return new Dispatcher(dispatcherID, name, login);
    }
}
