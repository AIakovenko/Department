package com.bionic.iakovenko.department.dao.mysql;

import static org.junit.Assert.*;

import com.bionic.iakovenko.department.dao.entity.Worker;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IWorker;
import org.junit.Test;
import org.junit.After;
import  org.junit.Before;

import java.util.List;
import java.util.Properties;

/**
 * @autor Alex Iakovenko
 * Date: 4/1/14
 * Time: 8:52 PM
 */
public class MySQLWorkerDAOTest {
    private DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IWorker workerDAO = factory.getWorkerDAO();

    @After
    public void tearDown() throws Exception {
        Worker insertedWorker = createTestWorker(2);
        workerDAO.deleteWorker(insertedWorker);

    }


    @Test
    public void testFileAll() throws Exception {
        List<Worker> list = workerDAO.findAll();
        assertNotNull(list);

    }
    @Test
    public void testFindWorkerByID() throws Exception {
        Worker testWorker = createTestWorker(1);
        short workerID = testWorker.getWorkerID();
        assertEquals(testWorker, workerDAO.findWorker(workerID));

    }
    @Test
    public void testFindWorkerByName() throws Exception {
        Worker testWorker = createTestWorker(1);
        String name = testWorker.getName();
        assertEquals(testWorker, workerDAO.findWorker(name).get(0));
    }

    @Test
    public void testInsertWorker() throws Exception {
        Worker insertedWorker = createTestWorker(2);
        assertTrue(workerDAO.insertWorker(insertedWorker));
    }
    @Test
    public void testDeleteWorker() throws Exception {
        Worker testWorker = createTestWorker(3);
        workerDAO.insertWorker(testWorker);
        assertTrue(workerDAO.deleteWorker(testWorker));
    }

    private Worker createTestWorker(int identifier){
        Worker testedPerson = new Worker((short)(10000-identifier), "Имя", "Специализация");
        return testedPerson;

    }
}
