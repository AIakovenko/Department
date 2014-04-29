package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Dispatcher;
import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.interfaces.IRequest;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import com.bionic.iakovenko.department.logger.SingleLogger;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 1:59 PM
 */
public class MySQLRequestDAO implements IRequest {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Selects from table Request all notes.
     *
     * @return list of Request objects.
     */
    @Override
    public List<Request> findAll() {
        query = "SELECT * FROM Request;";
        List<Request> list = null;

        try {
            list = new ArrayList();
            Request request;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                request = createRequest();
                list.add(request);
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
     * Selects from table Request note by id.
     *
     * @param requestId number of the request;
     * @return demanded request.
     */
    @Override
    public Request findRequest(int requestId) {
        query = "SELECT * FROM Request WHERE Request_ID = ?;";
        Request request = null;

        try {
            preparedStatement = connect(query);
            preparedStatement.setInt(1, requestId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            request = createRequest();
        } catch (SQLException e) {
            log.warn(e.toString());
            return null;
        } finally {
            disconnect();
        }
        return request;
    }

    /**
     * Selects from table Request by person id notes about requests person has done.
     * For implementation method must be taken person ID value from Person.
     * It can be done calling the method <code>getPersonID()</> from Person object.
     *
     * @param person Person object;
     * @return list of requests.
     */
    @Override
    public List<Request> findRequestByPerson(Person person) {
        query = "SELECT * FROM Request WHERE Person_ID = ?;";
        List<Request> list = null;

        try {
            list = new ArrayList();
            Request request;
            preparedStatement = connect(query);
            preparedStatement.setString(1, person.getPersonID());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                request = createRequest();
                list.add(request);
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
     * Selects from table Request by flat id notes about requests has been done for this flat.
     * For implementation method must be taken person ID value from Person.
     * It can be done calling the method <code>getFlatID()</> from Flat object.
     *
     * @param flat Flat object;
     * @return list of requests.
     */
    @Override
    public List<Request> findRequestByFlat(Flat flat) {
        query = "SELECT * FROM Request WHERE Flat_ID = ?;";
        List<Request> list = null;

        try {
            list = new ArrayList();
            Request request;
            preparedStatement = connect(query);
            preparedStatement.setShort(1, flat.getFlatID());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                request = createRequest();
                list.add(request);
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
     * Selects from table Request by works id notes about requests has been done with this works type.
     * For implementation method must be taken works ID value from Works.
     * It can be done calling the method <code>getWorksID()</> from Works object.
     *
     * @param works Works object;
     * @return list of requests.
     */
    @Override
    public List<Request> findRequestByWork(Works works) {
        query = "SELECT * FROM Request WHERE Works_ID = ?;";
        List<Request> list = null;

        try {
            list = new ArrayList();
            Request request;
            preparedStatement = connect(query);
            preparedStatement.setShort(1, works.getWorksID());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                request = createRequest();
                list.add(request);
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
     * Selects from table Request by requested time notes about requests has been done or will be planed.
     *
     * @param requestedDate requested date;
     * @param option        one of the constants:
     *                      <code>LESS</code>,
     *                      <code>LESS_OR_EQUAL</code>,
     *                      <code>EQUAL</code>,
     *                      <code>MORE</code>,
     *                      <code>MORE_OR_EQUAL</code>;
     * @return list of requests.
     */

    @Override
    public List<Request> findRequestByDate(Date requestedDate, int option) {
        switch (option) {
            case LESS:
                query = "SELECT * FROM Request WHERE Requested_time < ?;";
                break;
            case LESS_OR_EQUAL:
                query = "SELECT * FROM Request WHERE Requested_time <= ?;";
                break;
            case EQUAL:
                query = "SELECT * FROM Request WHERE Requested_time = ?;";
                break;
            case MORE_OR_EQUAL:
                query = "SELECT * FROM Request WHERE Requested_time >= ?;";
                break;
            case MORE:
                query = "SELECT * FROM Request WHERE Requested_time > ?;";
                break;
            default:
                throw new IllegalArgumentException("Incorrect argument!");
        }
        List<Request> list = null;

        try {
            list = new ArrayList();
            Request request;
            preparedStatement = connect(query);
            preparedStatement.setDate(1, requestedDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                request = createRequest();
                list.add(request);
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
     * Selects from table Request a note having the biggest id number.
     *
     * @return Request
     */
    @Override
    public Request findLastRequest() {
        query = "SELECT * FROM Request WHERE Request_ID = (SELECT max(Request_ID) FROM Request);";
        Request request = null;
        try {

            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                request = createRequest();
            } else return null;
        } catch (SQLException e) {
            log.warn(e.toString());
        } finally {
            disconnect();
        }
        return request;
    }

    /**
     * Looking for requests which was prepared by a client.
     *
     * @retirn Requests.
     */
    @Override
    public List<Request> findPreparedRequest() {
        query = "SELECT * FROM Request WHERE Dispatcher_ID = 0;";
        List<Request> list = null;
        try {
            list = new ArrayList<Request>();
            Request request;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                request = createRequest();
                list.add(request);
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
     * Inserts note about Request to Request table.
     *
     * @param request Request which can be inserted.
     * @return true         if note about request has been inserted.
     * false                if the exception has thrown.
     */
    @Override
    public boolean insertRequest(Request request) {

        Request lastRequest = findLastRequest();
        if (lastRequest != null) {
            request.setRequestID(lastRequest.getRequestID() + 1);
        } else {
            request.setRequestID(1);
        }
        query = "INSERT INTO Request Values (?,?,?,?,?,?);";
        try {
            preparedStatement = connect(query);

            preparedStatement.setInt(1, request.getRequestID());
            preparedStatement.setString(2, request.getPersonID());
            preparedStatement.setShort(3, request.getFlatID());
            preparedStatement.setShort(4, request.getWorksID());
            preparedStatement.setDate(5, request.getRequestedTime());
            preparedStatement.setShort(6, request.getDispatcherID());

            int result = preparedStatement.executeUpdate();
            return result == 1 ? true : false;
        } catch (SQLException e) {
            log.warn(e.toString());
        } finally {
            disconnect();
        }
        return false;
    }

    /**
     * Updates information about dispatcher working this request on.
     *
     * @param request    Request which can be updated.
     * @param dispatcher Dispatcher, who works request on;
     * @return true if note about request has been updated. false if the
     * exception has thrown.
     */
    @Override
    public boolean updateRequestByDispatcher(Request request, Dispatcher dispatcher) {
        query = "UPDATE Request SET Dispatcher_ID = (SELECT Dispatcher_ID FROM "
                + "Dispatcher WHERE Dispatcher_ID = ?) WHERE Request_ID = ?;";
        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, dispatcher.getDispatcherID());
            preparedStatement.setInt(2, request.getRequestID());
            int result = preparedStatement.executeUpdate();

            return result == 1 ? true : false;

        } catch (SQLException e) {
            log.warn(e.toString());
        } finally {
            disconnect();
        }
        return false;
    }

    /**
     * Removes note about Request from Request table.
     *
     * @param request Request which should be removed.
     * @return true             if note about request has been removed.
     * false            if the exception has thrown.
     */
    @Override
    public boolean deleteRequest(Request request) {
        query = "DELETE FROM Request WHERE Request_ID = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setInt(1, request.getRequestID());

            int result = preparedStatement.executeUpdate();
            return result == 1 ? true : false;

        } catch (SQLException e) {
            log.warn(e.toString());

        } finally {
            disconnect();
        }
        return false;
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
                log.warn(ex.toString());
            }
        }
        if (connection != null) {
            source.freeConnection(connection);
        }
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
}
