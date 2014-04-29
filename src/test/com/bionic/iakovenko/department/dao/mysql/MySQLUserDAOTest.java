package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Users;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IGroups;
import com.bionic.iakovenko.department.dao.interfaces.IUsers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/**
 * @autor Alex Iakovenko
 * Date: 4/9/14
 * Time: 7:28 PM
 */
public class MySQLUserDAOTest {
    DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    IUsers usersDAO = factory.getUsersDAO();

    @Test
    public void testFindAll() throws Exception {
        List<Users> list = usersDAO.findAll();
        assertTrue(list.size() >0);

    }

    @Test
    public void testFindUser() throws Exception {
        Users expectedUser = createTestUser();
        Users actualUser = usersDAO.findUser(expectedUser.getLogin());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFindUsersByGroup() throws Exception {
        byte groupID = 1;
        List<Users> list = usersDAO.findUsersByGroup(groupID);
        Users expectedUser = createTestUser();
        assertEquals(expectedUser, list.get(0));
    }

    @Test
    public void testInsertUser() throws Exception {
        Users expectedUser = new Users("Логин", "Пароль", IGroups.CLIENTS);
        assertTrue(usersDAO.insertUser(expectedUser));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Users expectedUser = new Users("Логин", "Пароль", IGroups.CLIENTS);
        assertTrue(usersDAO.deleteUser(expectedUser));
    }

    private Users createTestUser(){
        String login = "root";
        String password = "root";
        byte groupID = 1;

        return new Users(login, password, groupID);
    }
}
