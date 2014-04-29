package com.bionic.iakovenko.department.dao.factory;

import com.bionic.iakovenko.department.dao.interfaces.*;
import com.bionic.iakovenko.department.dao.mysql.*;


/**
 * @autor Alex Iakovenko
 * Date: 3/30/14
 * Time: 10:30 AM
 */
public class MySQLDAOFactory implements DBDAOFactory {


    /**
     * Returns an object which provides working with DB Person table.
     */
    @Override
    public IPerson getPersonDAO() {
        return new MySQLPersonDAO();

    }

    /**
     * Returns an object which provides working with DB Owner table.
     */
    @Override
    public IOwner getOwnerDAO() {
        return new MySQLOwnerDAO();

    }

    /**
     * Returns an object which provides working with DB Dispatcher table.
     */
    @Override
    public IDispatcher getDispatcherDAO() {
        return new MySQLDispatcherDAO();

    }

    /**
     * Returns an object which provides working with DB Flat table.
     */
    @Override
    public IFlat getFlatDAO() {
        return new MySQLFlatDAO();

    }

    /**
     * Returns an object which provides working with DB Request table.
     */
    @Override
    public IRequest getRequestDAO() {
        return new MySQLRequestDAO();

    }

    /**
     * Returns an object which provides working with DB Worker table.
     */
    @Override
    public IWorker getWorkerDAO() {
        return new MySQLWorkerDAO();

    }

    /**
     * Returns an object which provides working with DB Plan table.
     */
    @Override
    public IPlan getPlanDAO() {
        return new MySQLPlanDAO();

    }

    /**
     * Returns an object which provides working with DB Works table.
     */
    @Override
    public IWorks getWorksDAO() {
        return new MySQLWorksDAO();

    }

    /**
     * Returns an object which provides working with DB Users table.
     */
    @Override
    public IUsers getUsersDAO() {
        return new MySQLUserDAO();

    }

    /**
     * Returns an object which provides working with DB Groups table.
     */
    @Override
    public IGroups getGroupsDAO() {
        return new MySQLGroupsDAO();

    }
}
