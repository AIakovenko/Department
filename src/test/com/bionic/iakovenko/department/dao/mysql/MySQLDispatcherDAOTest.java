package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Dispatcher;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IDispatcher;

import org.junit.Test;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 2:53 PM
 */
public class MySQLDispatcherDAOTest {

    private  DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IDispatcher dispatcherDAO = factory.getDispatcherDAO();
    private Dispatcher exceptedDispatcher = new Dispatcher((short)10000, "Тест", "dispatcher_root");

    @Test
    public void testFindAll() throws Exception {
        List<Dispatcher> list = dispatcherDAO.findAll();
        assertTrue(list.contains(exceptedDispatcher));
    }

    @Test
    public void testFindDispatcher() throws Exception {
        short id = exceptedDispatcher.getDispatcherID();
        Dispatcher actualDispatcher = dispatcherDAO.findDispatcher(id);
        assertEquals(exceptedDispatcher, actualDispatcher);

    }

    @Test
    public void testFindDispatchers() throws Exception {
//        String expectedName = "Test User"; /*"Бабенко Е.Д.";*/
        String expectedName = exceptedDispatcher.getName();
        List<Dispatcher> list = dispatcherDAO.findDispatchers(expectedName);
        assertTrue(list.contains(exceptedDispatcher));

    }
    @Test
    public void testFindDispatcherByLogin() throws Exception{
        Dispatcher actualDispatcher = dispatcherDAO.findDispatchersByLogin(exceptedDispatcher.getLogin());
        assertEquals(exceptedDispatcher, actualDispatcher);
    }
    @Test
    public void testInsertDispatcher() throws Exception {
        Dispatcher c = new Dispatcher();
        String  name = "Тестовый диспетчер";
        short id = 9999;
        c.setDispatcherID(id);
        c.setName(name);
        assertTrue(dispatcherDAO.insertDispatcher(c));

    }

    @Test
    public void testDeleteDispatcher() throws Exception {
        Dispatcher c = new Dispatcher();
        String  name = "Тестовый диспетчер";
        short id = 9999;
        c.setDispatcherID(id);
        c.setName(name);
        dispatcherDAO.insertDispatcher(c);
        assertTrue(dispatcherDAO.deleteDispatcher(c));

    }



}
