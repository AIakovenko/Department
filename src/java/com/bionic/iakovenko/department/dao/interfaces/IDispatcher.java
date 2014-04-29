package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Dispatcher;

import java.util.List;

/**
 * The interface provides for working with a table Dispatcher.
 *
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 11:07 AM
 */
public interface IDispatcher {

    /**
     * Looking for all notes into table Dispatcher.
     * @return list of Dispatchers.
     */
    public List<Dispatcher> findAll();

    /**
     * Looking for one note by id into table Dispatcher.
     * @param dispatcherID      dispatcher id number;
     * @return                  Dispatcher object.
     */
    public Dispatcher findDispatcher(short dispatcherID);

    /**
     * Looking for dispatchers by name into table Dispatcher.
     * @param name              dispatcher's name;
     * @return                  Dispatchers.
     */
    public List<Dispatcher> findDispatchers(String name);

    /**
     * Looking for dispatchers by name into table Dispatcher.
     * @param login             dispatcher's login;
     * @return                  Dispatchers.
     */
    public Dispatcher findDispatchersByLogin(String login);

    /**
     * Adds note about Dispatcher to Dispatcher table.
     * @param dispatcher
     * @return true     if note about dispatcher has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertDispatcher(Dispatcher dispatcher);

    /**
     * Removes note about Dispatcher from Dispatcher table.
     * @param dispatcher
     * @return true     if note about dispatcher has been removed.
     *         false    if the exception has thrown.
     */
    public boolean deleteDispatcher(Dispatcher dispatcher);

}
