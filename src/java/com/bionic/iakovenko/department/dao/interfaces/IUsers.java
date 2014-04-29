package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Users;
import java.util.List;

/**
 * The interface provides for working with a table Users.
 *
 * @autor Alex Iakovenko
 * Date: 4/9/14
 * Time: 6:47 PM
 */
public interface IUsers {
    /**
     * Looking for all notes into table Users.
     * @return list of Users.
     */
    public List<Users> findAll();

    /**
     * Looking for one note by login name into table Users.
     * @param login      Login of user.
     * @return           User object.
     */
    public Users findUser(String login);


    /**
     * Looking for users by group into table Dispatcher.
     * @param groupID          Group number user belong to;
     * @return                 Users.
     */
    public List<Users> findUsersByGroup(byte groupID);

    /**
      Insert note about user to Users table.
     * @param user      User who should be inserted.
     * @return true     if note about user has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertUser(Users user);

    /**
     * Removes note about user from Users table.
     * @param user      User who should be removed.
     * @return true     if note about user has been removed.
     *         false    if the exception has thrown.
     */
    public boolean deleteUser(Users user);

}
