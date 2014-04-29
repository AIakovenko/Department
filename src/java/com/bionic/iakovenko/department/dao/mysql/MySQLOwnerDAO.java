package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.entity.Owner;
import com.bionic.iakovenko.department.dao.interfaces.IOwner;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import com.bionic.iakovenko.department.logger.SingleLogger;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 10:45 AM
 */
public class MySQLOwnerDAO implements IOwner {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Selects from table Owner all notes.
     *
     * @return list of Owner objects.
     */
    @Override
    public List<Owner> findAll() {
        query = "SELECT * FROM Owner;";
        List<Owner> list = null;

        try {
            list = new ArrayList<Owner>();
            Owner owner;
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                owner = createOwner();
                list.add(owner);
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
     * Selects from table Owner by person id notes about flats which person owns.
     * For implementation method must be taken person ID value from Person.
     * It can be done calling the method <code>getPersonID()</> from Person object.
     *
     * @param person person object;
     * @return list of flat objects.
     */
    @Override
    public List<Flat> findFlat(Person person) {
        query = "SELECT Flat.* FROM Flat, Owner WHERE Person_ID = ? " +
                "AND Owner.Flat_ID = Flat.Flat_ID;";
        List<Flat> list = null;

        try {
            list = new ArrayList<Flat>();
            Flat flat;
            preparedStatement = connect(query);
            preparedStatement.setString(1, person.getPersonID());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flat = createFlat();
                list.add(flat);
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
     * Selects from table Owner by flat id notes about persons are owned this flat.
     * For implementation method must be taken flat ID value from Flat.
     * It can be done calling the method <code>getFlatID()</> from Flat object.
     *
     * @param flat Flat object;
     * @return list of person objects.
     */
    @Override
    public List<Person> findPerson(Flat flat) {
        String query = "SELECT Person.* FROM Person, Owner WHERE Flat_ID = ? " +
                "AND Owner.Person_ID = Person.Person_ID;";
        List<Person> list = null;

        try {
            list = new ArrayList<Person>();
            Person person;
            preparedStatement = connect(query);
            preparedStatement.setShort(1, flat.getFlatID());
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
     * Inserts note about owner to Owner table.
     *
     * @param person Person object,
     * @param flat   Flat object.
     * @return true     if note about owner has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertOwner(Person person, Flat flat) {
        query = "INSERT INTO Owner VALUES(?, ?)";

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, person.getPersonID());
            preparedStatement.setShort(2, flat.getFlatID());
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
     * Removes note about owner from Owner table.
     *
     * @param person Person object,
     * @param flat   Flat object.
     * @return true     if note about owner has been removed.
     * false    if the exception has thrown.
     */
    @Override
    public boolean deleteOwner(Person person, Flat flat) {
        query = "DELETE FROM Owner WHERE Person_ID = ? AND Flat_ID = ?";

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, person.getPersonID());
            preparedStatement.setShort(2, flat.getFlatID());
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
     * Removes note about owner from Owner table by person's id.
     *
     * @param person Person object,
     * @return number of rows which have been removed.
     * if no one row has been removed then would be
     * returned 0.
     */
    @Override
    public int deleteOwnerByPerson(Person person) {
        query = "DELETE FROM Owner WHERE Person_ID = ?";

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, person.getPersonID());
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
     * Removes note about owner from Owner table by id of the flat.
     *
     * @param flat Flat object.
     * @return number of rows which have been removed.
     * if no one row has been removed then would be
     * returned 0.
     */
    @Override
    public int deleteOwnerByFlat(Flat flat) {
        String query = "DELETE FROM Owner WHERE Flat_ID = ?";

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, flat.getFlatID());
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

    private Owner createOwner() throws SQLException {
        String personID = resultSet.getString(1);
        short flatID = resultSet.getShort(2);
        return new Owner(personID, flatID);
    }

    private Flat createFlat() throws SQLException {
        short flatID = resultSet.getShort(1);
        String address = resultSet.getString(2);
        short building = resultSet.getShort(3);
        short apartment = resultSet.getShort(4);

        return new Flat(flatID, address, building, apartment);
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
