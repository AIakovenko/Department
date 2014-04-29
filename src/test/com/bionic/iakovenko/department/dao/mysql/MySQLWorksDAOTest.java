package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Works;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IWorks;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @autor Alex Iakovenko
 * Date: 4/1/14
 * Time: 10:16 PM
 */
public class MySQLWorksDAOTest {
    private DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IWorks worksDAO = factory.getWorksDAO();

    @After
    public void tearDown(){
        Works testWorks = createTestWorks(2);
        worksDAO.deleteWorks(testWorks);

    }

    @Test
    public void testFindAll() throws Exception {
        List<Works> list = worksDAO.findAll();
        assertNotNull(list);
    }
    @Test
    public void testFindWorksByID() throws Exception {
        Works testedWork = createTestWorks(1);
        short worksID = testedWork.getWorksID();
        assertEquals(testedWork, worksDAO.findWorks(worksID));

    }

    @Test
    public void testFindWorksByName() throws Exception {
        Works testWorks = createTestWorks(1);
        String name = testWorks.getName();
        assertEquals(testWorks, worksDAO.findWorks(name).get(0));
    }

    @Test
    public void testInsertWorks() throws Exception {
        Works testWorks = createTestWorks(2);
        assertTrue(worksDAO.insertWorks(testWorks));
    }

    @Test
    public void testDeleteWorks() throws Exception {
        Works testWorks = createTestWorks(3);
        worksDAO.insertWorks(testWorks);
        assertTrue(worksDAO.deleteWorks(testWorks));
    }

    private Works createTestWorks(int identifier){
        Works testedWork = new Works();
        testedWork.setWorksID((short)(10000 - identifier));
        testedWork.setName("Имя");
        testedWork.setDescription("Описание");
        return testedWork;

    }
}
