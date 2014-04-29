package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Flat;

import java.util.List;

/**
 * The interface provides for working with a table Flat.
 *
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 10:51 AM
 */
public interface IFlat {
    /**
     * Looking for all notices from table Flat.
     * @return list of Flat objects.
     */
    public List<Flat> findAll();

    /**
     * Looking for one note by id from table Flat.
     * @param flatId    id number;
     * @return Flat     Flat object.
     */
    public Flat findFlat(short flatId);

    /**
     * Looking for one note by address and apartment number from table Flat.
     * @param address           street name where apartment is;
     * @param building          building number.
     * @param apartment         apartment number.
     * @return                  Flat object.
     */
    public Flat findFlat(String address, short building, short apartment);

    /**
     * Looking for a note having the biggest id number from table Flat.
     * @return              Flat
     */
    public Flat findLastFlat();


    /**
     * Adds note about Flat to Flat table.
     * @param flat
     * @return true     if note about flat has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertFlat(Flat flat);

    /**
     * Removes note about Flat from Flat table.
     * @param flat
     * @return true     if note about flat has been removed.
     *         false    if the exception has thrown.
     */
    public boolean deleteFlat(Flat flat);
}
