package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.interfaces.IWorks;
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
public class MySQLWorksDAO implements IWorks {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Selects from table Works all notes.
     *
     * @return list of Works.
     */
    @Override
    public List<Works> findAll() {
        query = "SELECT * FROM Works;";
        List<Works> list = null;

        try {
            list = new ArrayList();
            Works work;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                work = createWork();
                list.add(work);
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
     * Selects from table Works one note by id.
     *
     * @param worksId work id number;
     * @return Works object.
     */
    @Override
    public Works findWorks(short worksId) {
        query = "SELECT * FROM Works WHERE Works_ID = ?;";
        Works work = null;

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, worksId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            work = createWork();
        } catch (SQLException e) {
            log.warn(e.toString(), e);
            return null;
        } finally {
            disconnect();
        }
        return work;
    }

    /**
     * Selects from table Works one note by name.
     *
     * @param name name of works;
     * @return Works object.
     */
    @Override
    public List<Works> findWorks(String name) {
        query = "SELECT * FROM Works WHERE Name = ?;";
        List<Works> list = null;

        try {
            list = new ArrayList();
            Works work;
            preparedStatement = connect(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                work = createWork();
                list.add(work);
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
     * Inserts note about Works to Works table.
     *
     * @param works
     * @return true     if note about Works has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertWorks(Works works) {
        query = "INSERT INTO Works VALUE (?, ?, ?);";

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, works.getWorksID());
            preparedStatement.setString(2, works.getName());
            preparedStatement.setString(3, works.getDescription());
            int result = preparedStatement.executeUpdate();
            return result == 1 ? true : false;

        } catch (SQLException e) {
            log.warn(e.toString(), e);
        }
        return false;
    }

    /**
     * Removes note about works from Works table.
     *
     * @param works the work which would be removed;
     * @return true     if note about Works has been removed.
     * false    if the exception has thrown.
     */
    @Override
    public boolean deleteWorks(Works works) {
        String query = "DELETE FROM Works WHERE Works_ID = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, works.getWorksID());
            int result = preparedStatement.executeUpdate();
            return result == 1 ? true : false;

        } catch (SQLException e) {
            log.warn(e.toString(), e);
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
                log.warn(ex.toString(), ex);
            }
        }
        if (connection != null) {
            source.freeConnection(connection);
        }
    }

    private Works createWork() throws SQLException {
        short worksID = resultSet.getShort(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        return new Works(worksID, name, description);
    }
}
