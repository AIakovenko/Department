package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.interfaces.IFlat;
import com.bionic.iakovenko.department.manager.ConnectionManager;
import com.bionic.iakovenko.department.logger.SingleLogger;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 1:58 PM
 */
public class MySQLFlatDAO implements IFlat {

    private static Logger log = SingleLogger.getInstance().getLog();
    private ConnectionManager source;
    private Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private String query;

    /**
     * Selects from table Flat all notes.
     *
     * @return list of Flat objects.
     */
    @Override
    public List<Flat> findAll() {
        query = "SELECT * FROM Flat;";
        List<Flat> list = null;
        try {
            list = new ArrayList<Flat>();
            Flat flat;
            preparedStatement = connect(query);
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
     * Selects from table Flat one note by id.
     *
     * @param flatId id number;
     * @return Flat     Flat object.
     */
    @Override
    public Flat findFlat(short flatId) {
        Flat flat = null;
        query = "SELECT * FROM Flat WHERE Flat_ID = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, flatId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            flat = createFlat();
        } catch (SQLException e) {
            log.warn(e.toString(), e);
            return null;
        } finally {
            disconnect();
        }
        return flat;
    }

    /**
     * Looking for one note by address and apartment number from table Flat.
     *
     * @param address   street name where apartment is;
     * @param building  building number.
     * @param apartment apartment number.
     * @return Flat object.
     */
    @Override
    public Flat findFlat(String address, short building, short apartment) {
        Flat flat = null;
        query = "SELECT * FROM Flat WHERE Address = ? AND Building = ? AND Apartment = ?;";

        try {
            preparedStatement = connect(query);
            preparedStatement.setString(1, address);
            preparedStatement.setShort(2, building);
            preparedStatement.setShort(3, apartment);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            flat = createFlat();
        } catch (SQLException e) {
            log.warn(e.toString(), e);
            return null;
        } finally {
            disconnect();
        }
        return flat;
    }

    /**
     * Selects from table Flat a note having the biggest id number.
     *
     * @return Flat
     */
    @Override
    public Flat findLastFlat() {
        query = "SELECT * FROM Flat WHERE Flat_ID = (SELECT max(Flat_ID) FROM Flat);";
        Flat flat = null;
        try {
            preparedStatement = connect(query);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            flat = createFlat();
        } catch (SQLException e) {
            log.warn(e.toString());
            return null;
        } finally {
            disconnect();
        }
        return flat;
    }

    /**
     * Inserts note about Flat to Flat table.
     *
     * @param flat
     * @return true     if note about flat has been inserted.
     * false    if the exception has thrown.
     */
    @Override
    public boolean insertFlat(final Flat flat) {

        Flat currentFlat = flat;
        Flat lastFlat = findLastFlat();
        if (lastFlat != null) {
            currentFlat.setFlatID((short) (lastFlat.getFlatID() + 1));
        } else {
            currentFlat.setFlatID((short) 1);
        }

        query = "INSERT INTO Flat VALUES (?, ?, ?, ?)";

        if (update(currentFlat, query)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes note about Flat from Flat table.
     *
     * @param flat
     * @return true     if note about flat has been removed.
     * false    if the exception has thrown.
     */
    @Override
    public boolean deleteFlat(Flat flat) {
        query = "DELETE FROM Flat WHERE Flat_ID = ? AND " +
                "Address = ? AND Building =? AND Apartment = ?";
        if (update(flat, query)) {
            return true;
        } else {
            return false;
        }
    }


    /*
     *Execute update-like queries.
     * @param query must be contained ControllerId and Name parameters.
     */
    private boolean update(Flat flat, String query) {

        short id = flat.getFlatID();
        String address = flat.getAddress();
        short building = flat.getBuilding();
        short apartment = flat.getApartment();

        try {
            preparedStatement = connect(query);
            preparedStatement.setShort(1, id);
            preparedStatement.setString(2, address);
            preparedStatement.setShort(3, building);
            preparedStatement.setShort(4, apartment);

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

    private Flat createFlat() throws SQLException {
        short flatID = resultSet.getShort(1);
        String address = resultSet.getString(2);
        short building = resultSet.getShort(3);
        short apartment = resultSet.getShort(4);

        return new Flat(flatID, address, building, apartment);
    }

}
