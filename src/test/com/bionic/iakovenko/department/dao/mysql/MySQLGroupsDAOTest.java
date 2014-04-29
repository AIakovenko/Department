package com.bionic.iakovenko.department.dao.mysql;

import com.bionic.iakovenko.department.dao.entity.Groups;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IGroups;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/**
 * @autor Alex Iakovenko
 * Date: 4/10/14
 * Time: 5:51 PM
 */
public class MySQLGroupsDAOTest {
    private DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IGroups groupsDAO = factory.getGroupsDAO();
    private Groups expectedGroup = new Groups((byte)1,"Test users");

    @Test
    public void testFindAll() throws Exception {
        List<Groups> list = groupsDAO.findAll();
        assertTrue(list.size() > 0);

    }


    @Test
    public void testFindGroup() throws Exception {
        Groups actualGroup = groupsDAO.findGroup(expectedGroup.getGroup_ID());
        assertEquals(expectedGroup, actualGroup);

    }

    @Test
    public void testFindGroupsByDescription() throws Exception {
        List<Groups> list = groupsDAO.findGroupsByDescription(expectedGroup.getDescription());
        assertTrue(list.contains(expectedGroup));

    }

    @Test
    public void testInsertGroup() throws Exception {
        Groups insertedGroup = createTestGroup();
        assertTrue (groupsDAO.insertGroup(insertedGroup));

    }

    @Test
    public void testDeleteGroup() throws Exception {
        Groups deletedGroup = createTestGroup();
        groupsDAO.insertGroup(deletedGroup);
        assertTrue (groupsDAO.deleteGroup(deletedGroup));

    }

    private Groups createTestGroup(){
        byte groupID = 2;
        String description = "Inserted by unit test";
        return new Groups(groupID, description);
    }
}
