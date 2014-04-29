package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Groups;
import com.bionic.iakovenko.department.dao.interfaces.IGroups;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 4/9/14
 * Time: 7:39 PM
 */
public class MySQLGroupsDAO implements IGroups {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Looking for all notes into table Groups.
     *
     * @return list of Groups.
     */
    @Override
    public List<Groups> findAll() {
        query = "SELECT * FROM Groups;";
        List<Groups> list = null;
        try {
            list = new ArrayList<Groups>();
            Groups group;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                group = createGroups();
                list.add(group);
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
     * Looking for one note by group id into table Groups.
     *
     * @param groupID group number;
     * @return Group object.
     */
    @Override
    public Groups findGroup(byte groupID) {
        query = "SELECT * FROM Groups WHERE Group_ID=?;";
        Groups group = null;
        try {
            preparedStatement = connect(query);
            preparedStatement.setByte(1, groupID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                group = createGroups();
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.warn(e.toString(), e);
        } finally {
            disconnect();
        }
        return group;
    }

    /**
     * Looking for group by description into table Groups.
     *
     * @param description Description of the group;
     * @return Groups.
     */
    @Override
    public List<Groups> findGroupsByDescription(String description) {
        query = "SELECT * FROM Groups WHERE Description = ?;";
        List<Groups> list = null;
        try {
            list = new ArrayList<Groups>();
            Groups group;
            preparedStatement = connect(query);
            preparedStatement.setString(1, description);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                group = createGroups();
                list.add(group);
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
     * Insert note about group to Groups table.
     *
     * @param group Group of users.
     * @return true     if note about group has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertGroup(Groups group) {
        query = "INSERT INTO Groups VALUES (?, ?);";
        try {
            preparedStatement = connect(query);
            preparedStatement.setByte(1, group.getGroup_ID());
            preparedStatement.setString(2, group.getDescription());
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
     * Removes note about user from Users table.
     *
     * @param group Group of users.
     * @return true     if note about group has been removed.
     * false    if the exception has thrown.
     */
    @Override
    public boolean deleteGroup(Groups group) {
        query = "DELETE FROM Groups WHERE Group_ID = ?;";
        try {
            preparedStatement = connect(query);
            preparedStatement.setByte(1, group.getGroup_ID());
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

    private Groups createGroups() throws SQLException {
        byte groupID = resultSet.getByte(1);
        String description = resultSet.getString(2);
        return new Groups(groupID, description);
    }
}
