package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.interfaces.IPerson;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import com.bionic.iakovenko.department.logger.SingleLogger;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 10:07 AM
 */
public class MySQLPersonDAO implements IPerson {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Selects from table Person all notes.
     *
     * @return list of Person.
     */
    @Override
    public List<Person> findAll() {
        query = "SELECT * FROM Person;";
        List<Person> list = null;
        try {
            list = new ArrayList<Person>();
            Person person;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person = createPerson();
                list.add(person);
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
     * Selects from table Person one note by id.
     *
     * @param personId person id number;
     * @return Person object.
     */
    @Override
    public Person findPerson(String personId) {
        query = "SELECT * FROM Person WHERE Person_ID = ?;";
        Person person = null;

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, personId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            person = createPerson();
        } catch (SQLException e) {
            log.warn(e.toString(), e);
            return null;
        } finally {
            disconnect();
        }
        return person;
    }

    /**
     * Selects from table Person one note by name.
     *
     * @param familyName     person's familyName;
     * @param givenName      person's name;
     * @param additionalName person's additional name;
     * @return Person object.
     */
    @Override
    public List<Person> findPerson(String familyName, String givenName, String additionalName) {

        query = "SELECT * FROM Person WHERE First_name = ? AND Name = ? " +
                "AND Last_name = ?;";
        List<Person> list = null;

        try {
            list = new ArrayList<Person>();
            Person person;
            preparedStatement = connect(query);
            preparedStatement.setString(1, familyName);
            preparedStatement.setString(2, givenName);
            preparedStatement.setString(3, additionalName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                person = createPerson();
                list.add(person);
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
     * Selects from table Person one note by name.
     *
     * @param login person's login;
     * @return Person object.
     */
    @Override
    public Person findPersonByLogin(String login) {
        query = "SELECT * FROM Person WHERE Login = ?;";
        Person person = null;
        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                person = createPerson();
            } else {
                return null;
            }

        } catch (SQLException e) {
            log.warn(e.toString());
        } finally {
            disconnect();
        }
        return person;
    }

    /**
     * Inserts note about Person to Person table.
     *
     * @param person
     * @return true     if note about person has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertPerson(Person person) {
        query = "INSERT INTO Person VALUES (?, ?, ?, ?, ?);";

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, person.getPersonID());
            preparedStatement.setString(2, person.getFamilyName());
            preparedStatement.setString(3, person.getGivenName());
            preparedStatement.setString(4, person.getAdditionalName());
            preparedStatement.setString(5, person.getLogin());
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
     * Removes note about person from Person table.
     *
     * @param person
     * @return true     if note about person has been removed.
     * false    if the exception has thrown.
     */
    @Override
    public boolean deletePerson(Person person) {
        query = "DELETE FROM Person WHERE Person_ID = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, person.getPersonID());
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

    private Person createPerson() throws SQLException {
        String personID = resultSet.getString(1);
        String familyName = resultSet.getString(2);
        String givenName = resultSet.getString(3);
        String additionalName = resultSet.getString(4);
        String login = resultSet.getString(5);

        return new Person(personID, familyName, givenName, additionalName, login);
    }

}
