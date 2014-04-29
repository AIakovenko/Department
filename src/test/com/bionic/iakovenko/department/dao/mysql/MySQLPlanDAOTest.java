package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Plan;
import com.bionic.iakovenko.department.dao.entity.Request;
import com.bionic.iakovenko.department.dao.entity.Worker;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IPlan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @autor Alex Iakovenko
 * Date: 4/2/14
 * Time: 11:38 AM
 */
public class MySQLPlanDAOTest {
    private DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IPlan planDAO = factory.getPlanDAO();

    @After
    public void tearDown() throws Exception {
        Worker testedWorker = createWorker(2);
        Request testedRequest = createTestRequest(2);
        planDAO.deletePlan(testedRequest, testedWorker);


    }

    @Test
    public void testFindAll() throws Exception {
        List<Plan> list = planDAO.findAll();
        assertTrue(list.size() > 0);
    }

    @Test
    public void testFindRequest() throws Exception {
        Request testedRequest = createTestRequest(1);
        Worker testedWorker = createWorker(1);
        assertEquals(testedRequest.getRequestID(),
                planDAO.findRequest(testedWorker).get(0).getRequestID());
    }

    @Test
    public void testFindWorker() throws Exception {
        Worker testedWorker = createWorker(1);
        Request testedRequest = createTestRequest(1);

        assertEquals(testedWorker,
                planDAO.findWorker(testedRequest).get(0));
    }

    @Test
    public void testInsertPlan() throws Exception {
        Worker expectedWorker = new Worker();
        expectedWorker.setWorkerID((short)9999);
        Request expectedRequest = new Request();
        expectedRequest.setRequestID(1);
        assertTrue(planDAO.insertPlan(expectedRequest, expectedWorker));
    }

    @Test
    public void testDeletePlan() throws Exception {
        Worker testedWorker = createWorker(3);
        Request testedRequest = createTestRequest(3);
        planDAO.insertPlan(testedRequest, testedWorker);
        assertTrue(planDAO.deletePlan(testedRequest, testedWorker));
    }

    @Test
    public void testDeletePlanByRequest() throws Exception {
        Worker testedWorker = createWorker(4);
        Request testedRequest = createTestRequest(4);
        planDAO.insertPlan(testedRequest, testedWorker);
        assertTrue(1 == planDAO.deletePlanByRequest(testedRequest));
    }

    @Test
    public void testDeletePlanByWorker() throws Exception {
        Worker testedWorker = createWorker(5);
        Request testedRequest = createTestRequest(5);
        planDAO.insertPlan(testedRequest, testedWorker);
        assertTrue(1 == planDAO.deletePlanByWorker(testedWorker));
    }


 /*==========================================================================*/
    private Request createTestRequest(int identifier){
        int requestID = identifier;
        String personID = "ZZ999999";
        short flatID = 99;
        short worksID = 9999;
        Date requestedTime = new Date(System.currentTimeMillis());
        short dispatcherID = 0;

        return new Request(requestID, personID, flatID, worksID, requestedTime, dispatcherID);

    }
    protected Plan createTestPlan (int identifier){
        int requestID = identifier;
        short workerID  = (short)(10000 - identifier);

        return new Plan (requestID, workerID);
    }
    protected Worker createWorker(int identifier){
        short workerID = (short)(10000 - identifier);
        String name = "Имя";
        String specialization = "Специализация";
        return new Worker(workerID, name, specialization);
    }
}
