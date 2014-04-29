package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Users;
import com.bionic.iakovenko.department.dao.interfaces.IUsers;
import com.bionic.iakovenko.department.logger.SingleLogger;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 4/9/14
 * Time: 7:16 PM
 */
public class MySQLUserDAO implements IUsers {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Looking for all notes into table Users.
     *
     * @return list of Users.
     */
    @Override
    public List<Users> findAll() {
        query = "SELECT * FROM Users;";
        List<Users> list = null;
        try {
            list = new ArrayList<Users>();
            Users user;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = createUser();
                list.add(user);
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
     * Looking for one note by login name into table Users.
     *
     * @param login Login of user.
     * @return User object.
     */
    @Override
    public Users findUser(String login) {
        query = "SELECT * FROM Users WHERE Login=?;";
        Users currentUser = null;
        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                currentUser = createUser();
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.warn(e.toString(), e);
        } finally {
            disconnect();
        }
        return currentUser;
    }

    /**
     * Looking for users by group into table Dispatcher.
     *
     * @param groupID Group number user belong to;
     * @return Users.
     */
    @Override
    public List<Users> findUsersByGroup(byte groupID) {
        query = "SELECT * FROM Users WHERE Group_ID=?;";
        List<Users> list = null;
        try {
            list = new ArrayList<Users>();
            Users user;
            preparedStatement = connect(query);
            preparedStatement.setByte(1, groupID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = createUser();
                list.add(user);
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
     * Insert note about user to Users table.
     * Use the IGroup constants.
     *
     * @param user User who should be inserted.
     * @return true     if note about user has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertUser(Users user) {

        query = "INSERT INTO Users VALUES (?, ?, (SELECT Group_ID FROM Groups Where Group_ID = ?));";

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setByte(3, user.getGroup_ID());
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
     * @param user User who should be removed.
     * @return true     if note about user has been removed.
     * false    if the exception has thrown.
     */
    @Override
    public boolean deleteUser(Users user) {

        query = "DELETE FROM Users WHERE Login = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, user.getLogin());
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

    private Users createUser() throws SQLException {
        String login = resultSet.getString(1);
        String password = resultSet.getString(2);
        byte groupID = resultSet.getByte(3);
        return new Users(login, password, groupID);
    }
}
