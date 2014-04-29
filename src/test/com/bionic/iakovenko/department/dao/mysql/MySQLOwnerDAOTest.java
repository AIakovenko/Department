package com.bionic.iakovenko.department.dao.mysql;

import static org.junit.Assert.*;

import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IOwner;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import java.util.List;
import java.util.Properties;

/**
 * @autor Alex Iakovenko
 * Date: 4/1/14
 * Time: 10:18 AM
 */
public class MySQLOwnerDAOTest {
    private DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IOwner ownerDAO = factory.getOwnerDAO();

    @Test
    public void testFindAll() throws Exception {
        List list = ownerDAO.findAll();
        assertTrue(list.size()>0);

    }
    @Test
    public void testFindFlat() throws Exception {
        Flat expectedFlat = createTestFlat(1);

        Person testedPerson = createTestPerson(9);

        List<Flat> receivedFlat = ownerDAO.findFlat(testedPerson);
        assertEquals(expectedFlat, receivedFlat.get(0));

    }
    @Test
    public void testFindPerson() throws Exception {
        Person expectedPerson = createTestPerson(9);
        Flat testedFlat = createTestFlat(1);
        List<Person> list = ownerDAO.findPerson(testedFlat);

        assertTrue(list.contains(expectedPerson));


    }
    @Test
    public void testInsertOwner() throws Exception {
        Person person = createTestPerson(2);
        Flat flat = createTestFlat(2);
        assertTrue(ownerDAO.insertOwner(person, flat));
    }

    @Test
    public void testDeleteOwner() throws Exception {
        Person person = createTestPerson(3);
        Flat flat = createTestFlat(3);
        ownerDAO.insertOwner(person, flat);
        assertTrue(ownerDAO.deleteOwner(person, flat));
    }

    @Test
    public void testDeleteOwnerByPerson() throws Exception {
        Person person = createTestPerson(4);
        Flat flat = createTestFlat(4);
        ownerDAO.insertOwner(person, flat);

        assertTrue(0 < ownerDAO.deleteOwnerByPerson(person));
    }

    @Test
    public void testDeleteOwnerByFlat() throws Exception {
        Person person = createTestPerson(5);
        Flat flat = createTestFlat(5);
        ownerDAO.insertOwner(person, flat);

        assertTrue(0 < ownerDAO.deleteOwnerByFlat(flat));
    }

    @After
    public void tearDown() throws Exception {
        Person person = createTestPerson(2);
        Flat flat = createTestFlat(2);
        ownerDAO.deleteOwner(person, flat);

    }

    /*==============================================================================*/
    private Person createTestPerson(int identifier){
        Person testedPerson = new Person();
        testedPerson.setPersonID("ZZ99999" + identifier);
        testedPerson.setFamilyName("Фамилия");
        testedPerson.setGivenName("Имя");
        testedPerson.setAdditionalName("Отчество");
        testedPerson.setLogin("client_root");
        return testedPerson;

    }

    private Flat createTestFlat(int identifier){
        Flat expectedFlat = new Flat();
        expectedFlat.setFlatID((short)(100-identifier));
        expectedFlat.setAddress("Адресс");
        expectedFlat.setBuilding((short)0);
        expectedFlat.setApartment((short)1);

        return expectedFlat;
    }
}
