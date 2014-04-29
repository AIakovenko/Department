package com.bionic.iakovenko.department.dao.interfaces;

import com.bionic.iakovenko.department.dao.entity.Flat;
import com.bionic.iakovenko.department.dao.entity.Owner;
import com.bionic.iakovenko.department.dao.entity.Person;
import java.util.List;

/**
 * The interface provides for working with a table Owner
 *
 * @autor Alex Iakovenko
 * Date: 3/31/14
 * Time: 9:44 AM
 */
public interface IOwner {
    /**
     * Selects from table Owner all notes.
     * @return list of Owner objects.
     */
    public List<Owner> findAll();

    /**
     * Selects from table Owner by person id notes about flats which person owns.
     * For implementation method must be taken person ID value from Person.
     * It can be done calling the method <code>getPersonID()</> from Person object.
     * @param person person object;
     * @return list of flat objects.
     */
    public List<Flat> findFlat(Person person);

    /**
     * Selects from table Owner by flat id notes about persons are owned this flat.
     * For implementation method must be taken flat ID value from Flat.
     * It can be done calling the method <code>getFlatID()</> from Flat object.
     * @param flat Flat object;
     * @return list of person objects.
     */
    public List<Person> findPerson(Flat flat);

    /**
     * Inserts note about owner to Owner table.
     * @param person    Person object,
     * @param flat      Flat object.
     * @return true     if note about owner has been inserted.
     *         false    if the exception has thrown.
     */
    public boolean insertOwner(Person person, Flat flat);

    /**
     * Removes person and flat which bind each other from Owner table.
     * @param person    Person object.
     * @param flat      Flat object.
     * @return true     if note about owner has been removed.
     *         false    if the exception has thrown.
     */

    public boolean deleteOwner(Person person, Flat flat);
    /**
     * Removes note about owner from Owner table by person's id.
     * @param person    Person object,
     * @return          number of rows which have been removed.
     *                  if no one row has been removed then would be
     *                  returned 0.
     */
    public int deleteOwnerByPerson(Person person);

    /**
     * Removes note about owner from Owner table by id of the flat.
     * @param flat      Flat object.
     * @return          number of rows which have been removed.
     *                  if no one row has been removed then would be
     *                  returned 0.
     */
    public int deleteOwnerByFlat(Flat flat);

}
