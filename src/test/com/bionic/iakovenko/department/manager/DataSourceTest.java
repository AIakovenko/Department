package com.bionic.iakovenko.department.manager;


import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Statement;
import java.sql.Connection;

/**
 * @autor Alex Iakovenko
 * Date: 3/28/14
 * Time: 4:13 PM
 */
public class DataSourceTest {
    Connection connection = null;
    Statement statement = null;
    @Test
    public void testGetInstance() throws Exception {
    }
    @Test
    public void testGetConnection() throws Exception {
        statement = connection.createStatement();
        assertTrue(statement.execute("SELECT * FROM Person"));
        connection.close();

    }
}
