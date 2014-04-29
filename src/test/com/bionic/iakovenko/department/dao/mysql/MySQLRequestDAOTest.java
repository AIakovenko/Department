package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.*;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IRequest;
import org.junit.After;
import org.junit.Test;

import java.sql.Date;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

import javax.sound.sampled.CompoundControl;

/**
 * @autor Alex Iakovenko
 * Date: 4/3/14
 * Time: 12:47 AM
 */
public class MySQLRequestDAOTest {

    private DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IRequest requestDAO = factory.getRequestDAO();
    private Request expectedRequest = new Request(1, "ZZ999999", (short)99, (short)9999,
            Date.valueOf("2014-04-02"), (short)0);



    @Test
    public void testFindAll() throws Exception {
        List<Request> list = requestDAO.findAll();
        assertTrue(list.contains(expectedRequest));


    }

    @Test
    public void testFindLastRequest() throws Exception{
        List<Request> list = requestDAO.findAll();
        Request expectedRequest = list.get(list.size()-1);
        Request request = requestDAO.findLastRequest();
        assertEquals(request, expectedRequest);
    }

    @Test
    public void testFindRequestByID() throws Exception {

        int requestID = expectedRequest.getRequestID();
        Request actualRequest = requestDAO.findRequest(requestID);
        assertEquals(expectedRequest, actualRequest);

    }

    @Test
    public void testFindRequestByPerson() throws Exception {

        Person person = new Person();
        person.setPersonID(expectedRequest.getPersonID());

        Request actualRequest = requestDAO.findRequestByPerson(person).get(0);
        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void testFindRequestByFlat() throws Exception {


        Flat flat = new Flat();
        flat.setFlatID(expectedRequest.getFlatID());

        Request actualRequest = requestDAO.findRequestByFlat(flat).get(0);
        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void testFindRequestByWork() throws Exception {
        Works works = new Works();
        works.setWorksID(expectedRequest.getWorksID());

        Request actualRequest = requestDAO.findRequestByWork(works).get(0);
        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void testFindRequestByDateLess() throws Exception {
        Date date = Date.valueOf("2015-04-02");
        assertEquals(expectedRequest, requestDAO.findRequestByDate(date, IRequest.LESS).get(0));
    }
    @Test
    public void testFindRequestByDateEqual() throws Exception {
        Date date = Date.valueOf("2014-04-02");
        assertEquals(expectedRequest, requestDAO.findRequestByDate(date, IRequest.EQUAL).get(0));
    }

    @Test
    public void testFindRequestByDateMore() throws Exception {
        Date date = Date.valueOf("2013-04-02");
        assertEquals(expectedRequest, requestDAO.findRequestByDate(date, IRequest.MORE).get(0));
    }
    @Test
    public void testFindPreparedRequest() throws Exception {
        List<Request> list = requestDAO.findPreparedRequest();

        assertTrue(list.contains(expectedRequest));
    }

    @Test
    public void testInsertRequest() throws Exception {
        Request request = new Request(2, "ZZ999999", (short)99, (short)9999,
                Date.valueOf("2014-04-02"), (short)0);
        assertTrue(requestDAO.insertRequest(request));
        requestDAO.deleteRequest(request);
    }
    
    @Test
    public void testUpdateRequestByDispatcher() throws Exception {
        Request expectedRequest = new Request();
        expectedRequest.setRequestID(1);
        Dispatcher expectedDispatcher = new Dispatcher();
        expectedDispatcher.setDispatcherID((short)10000);
        assertTrue(requestDAO.updateRequestByDispatcher(expectedRequest, expectedDispatcher));
        expectedDispatcher.setDispatcherID((short) 0);
        requestDAO.updateRequestByDispatcher(expectedRequest, expectedDispatcher);
    }

    @Test
    public void testDeleteRequest() throws Exception {
        Request request = new Request(2, "ZZ999999", (short)99, (short)9999,
                Date.valueOf("2014-04-02"), (short)0);

        requestDAO.insertRequest(request);
        assertTrue(requestDAO.deleteRequest(request));

    }


}
