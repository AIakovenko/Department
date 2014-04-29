package com.bionic.iakovenko.department.dao.factory;

import com.bionic.iakovenko.department.dao.interfaces.*;


/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 3:21 PM
 */
public interface DBDAOFactory {
    /**
     * Returns an object which provides working with DB Person table.
     */
    public IPerson getPersonDAO();

    /**
     * Returns an object which provides working with DB Owner table.
     */
    public IOwner getOwnerDAO();

    /**
     * Returns an object which provides working with DB Dispatcher table.
     */
    public IDispatcher getDispatcherDAO();

    /**
     * Returns an object which provides working with DB Flat table.
     */
    public IFlat getFlatDAO();

    /**
     * Returns an object which provides working with DB Request table.
     */
    public IRequest getRequestDAO();

    /**
     * Returns an object which provides working with DB Worker table.
     */
    public IWorker getWorkerDAO();

    /**
     * Returns an object which provides working with DB Plan table.
     */
    public IPlan getPlanDAO();

    /**
     * Returns an object which provides working with DB Works table.
     */
    public IWorks getWorksDAO();

    /**
     * Returns an object which provides working with DB Users table.
     */
    public IUsers getUsersDAO();

    /**
     * Returns an object which provides working with DB Groups table.
     */
    public IGroups getGroupsDAO();
}
