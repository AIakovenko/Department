package com.bionic.iakovenko.department.dao.mysql;

import static org.junit.Assert.*;

import com.bionic.iakovenko.department.dao.entity.Person;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IPerson;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import java.util.List;
import java.util.Properties;

/**
 * @autor Alex Iakovenko
 * Date: 4/1/14
 * Time: 1:57 PM
 */
public class MySQLPersonDAOTest{
    private DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IPerson personDAO = factory.getPersonDAO();

    @Test
    public void tesFindAll() throws Exception {
        List<Person> list = personDAO.findAll();
         assertTrue(list.size() > 0);
    }

    @Test
    public void testFindPersonByID() throws Exception {
        Person expectedPerson = createTestPerson(9);
        String personId = expectedPerson.getPersonID();

        assertEquals(expectedPerson, personDAO.findPerson(personId));

    }

    @Test
    public void testFindPersonByNames() throws Exception {
        Person expectedPerson = createTestPerson(9);
        String familyName = expectedPerson.getFamilyName();
        String givenName = expectedPerson.getGivenName();
        String additionalName = expectedPerson.getAdditionalName();
        assertEquals(expectedPerson, personDAO.findPerson(familyName, givenName, additionalName).get(0));


    }
    @Test
    public void testFindPersonByLogin() throws Exception{
        Person expectedPerson = createTestPerson(9);
        Person actualPerson = personDAO.findPersonByLogin(expectedPerson.getLogin());
        assertEquals(expectedPerson, actualPerson);

    }

    @Test
    public void testInsertPerson() throws Exception {
        Person addedPerson = createTestPerson(1);
        assertTrue(personDAO.insertPerson(addedPerson));

    }

    @Test
    public void testDeletePerson() throws Exception {
        Person addedPerson = createTestPerson(2);
        personDAO.insertPerson(addedPerson);
        assertTrue(personDAO.deletePerson(addedPerson));

    }

    @After
    public void tearDown(){
        Person addedPerson = createTestPerson(1);
        personDAO.deletePerson(addedPerson);
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
}
