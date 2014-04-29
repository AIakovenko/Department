package com.bionic.iakovenko.department.dao.mysql;


import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.factory.DBDAOFactory;
import com.bionic.iakovenko.department.dao.factory.DAOFactory;
import com.bionic.iakovenko.department.dao.factory.DbType;
import com.bionic.iakovenko.department.dao.interfaces.IFlat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 11:45 PM
 */
public class MySQLFlatDAOTest{
    private DBDAOFactory factory = DAOFactory.getFactory(DbType.MY_SQL);
    private IFlat flatDAO = factory.getFlatDAO();
    private final Flat expectedFlat = new Flat ((short)99, "Адресс", (short)0, (short)1);
    private final Flat insertedFlat = new Flat((short)0, "Тестовый Адресс", (short)0, (short)0);

    @Test
    public void testFindAll() throws Exception {
        List list = flatDAO.findAll();
        assertTrue(list.contains(expectedFlat));

    }

    @Test
    public void testFindLastFlat() throws Exception {
        List<Flat> list = flatDAO.findAll();
        Flat expectedFlat = list.get(list.size()-1);
        Flat flat = flatDAO.findLastFlat();
        assertEquals(expectedFlat, flat);
    }

    @Test
    public void testFindFlatId() throws Exception {
        short id = expectedFlat.getFlatID();
        Flat flat = flatDAO.findFlat(id);
        assertEquals(expectedFlat, flat);
    }

    @Test
    public void testFindFlatAddress() throws Exception {
        String address = expectedFlat.getAddress();
        short building = expectedFlat.getBuilding();
        short apartment = expectedFlat.getApartment();


        Flat flat = flatDAO.findFlat(address, building, apartment);
        assertEquals(expectedFlat, flat);

    }


    @Test
    public void testInsertFlat() throws Exception {

        assertTrue(flatDAO.insertFlat(insertedFlat));
        flatDAO.deleteFlat(insertedFlat);

    }

    @Test
    public void testDeleteFlat() throws Exception {

        flatDAO.insertFlat(insertedFlat);
        assertTrue(flatDAO.deleteFlat(insertedFlat));

    }

}
