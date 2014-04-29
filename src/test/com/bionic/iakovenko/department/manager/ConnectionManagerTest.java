package com.bionic.iakovenko.department.manager;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Statement;
import java.sql.Connection;

/**
 * @autor Alex Iakovenko
 * Date: 4/11/14
 * Time: 1:59 PM
 */
public class ConnectionManagerTest {

    private ConnectionManager manager;
    private Connection connection;
    private Statement statement;

    @Test
    public void testGetInstance() throws Exception {
        manager = ConnectionManager.getInstance();
        assertNotNull(manager);
    }
    @Test
    public void testGetConnection() throws Exception {
        manager = ConnectionManager.getInstance();
        connection = manager.getConnection();
        statement = connection.createStatement();
        assertTrue(statement.execute("SELECT * FROM Groups;"));

    }
}
