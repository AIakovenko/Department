package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Groups;

import java.util.List;

/**
 * The interface provides for working with a table Groups
 * @autor Alex Iakovenko
 * Date: 4/9/14
 * Time: 7:02 PM
 */
public interface IGroups {

    public static final byte DISPATCHERS = 10;
    public static final byte CLIENTS = 11;

    /**
     * Looking for all notes into table Groups.
     * @return list of Groups.
     */
    public List<Groups> findAll();

    /**
     * Looking for one note by group id into table Groups.
     * @param groupID    group number;
     * @return           Group object.
     */
    public Groups findGroup(byte groupID);

    /**
     * Looking for group by description into table Groups.
     * @param description      Description of the group;
     * @return                 Groups.
     */
    public List<Groups> findGroupsByDescription(String description);

    /**
     Insert note about group to Groups table.
     * @param group     Group of users.
     * @return true     if note about group has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertGroup(Groups group);

    /**
     * Removes note about user from Users table.
     * @param group     Group of users.
     * @return true     if note about group has been removed.
     *         false    if the exception has thrown.
     */
    public boolean deleteGroup(Groups group);


}
